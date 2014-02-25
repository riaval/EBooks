package ua.miratech.zhukov.service.implementation;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.miratech.zhukov.dto.output.Book;
import ua.miratech.zhukov.dto.output.DownloadBook;
import ua.miratech.zhukov.mapper.BookMapper;
import ua.miratech.zhukov.service.*;
import ua.miratech.zhukov.util.thread.UnCompressCallable;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.util.component.EbookStorage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private BookService bookService;

	@Autowired
	private EbookStorage ebookStorage;

	@Autowired
	private ConverterService converterService;

	@Autowired()
	@Qualifier("executorService")
	private ExecutorService service;

	public DownloadBook getDownloadBook(Book book) {
		String filePath = ebookStorage.getMainCatalogue() + book.getStoredIndex() + "." + book.getExtension();
		String fileName = book.getFileName();
		return new DownloadBook(filePath, fileName);
	}

	@Override
	public List<UploadedFile> uploadFile(Map<String, MultipartFile> filesMap) throws IOException {
		List<UploadedFile> files = new ArrayList<>();

		for (String fileName : filesMap.keySet()) {
			MultipartFile mpf = filesMap.get(fileName);

			UploadedFile uploadedFile = new UploadedFile(
					mpf.getBytes(),
					mpf.getOriginalFilename(),
					mpf.getSize(),
					mpf.getContentType()
			);

			String extension = FilenameUtils.getExtension(uploadedFile.getName());
			if ("epub".equals(extension) || "mobi".equals(extension)) {
				converterService.convertToFb2(uploadedFile);
			}

			String userEmail = securityService.getUserEmail();
			if ("application/zip".equals(uploadedFile.getType())) {
				service.submit(new UnCompressCallable(this, uploadedFile, userEmail));
			} else {
				Long id = bookService.addBook(uploadedFile, userEmail);
				uploadedFile.setDeleteUrl("/Ebooks/book/delete/" + id);
				uploadedFile.setType("text");
			}

			files.add(uploadedFile);
		}

		return files;
	}

	public void uploadZipFile(UploadedFile uf, String userEmail) throws IOException {
		Long systemTime = Calendar.getInstance().getTime().getTime();
		String zipDirPath = ebookStorage.getTempCatalogue() + systemTime;
		String zipFilePath = zipDirPath + "." + FilenameUtils.getExtension(uf.getName());

		File zipDir = new File(zipDirPath);
		File zipFile = new File(zipFilePath);

		FileCopyUtils.copy(uf.getBytes(), new FileOutputStream(zipFilePath));
		extractZip(zipFilePath);

		Collection files = FileUtils.listFiles(zipDir, new String[]{"fb2"}, true);
		for (Object each : files) {
			File file = (File) each;
			Path filePath = Paths.get(file.getAbsolutePath());
			UploadedFile uploadedFile = new UploadedFile(
					Files.readAllBytes(filePath),
					file.getName(),
					file.length(),
					null
			);

			bookService.addBook(uploadedFile, userEmail);
		}

		FileUtils.deleteDirectory(zipDir);
		zipFile.delete();
	}

	public void deleteFile(Long storedIndex) {
		File dir = new File(ebookStorage.getMainCatalogue());
		FileFilter fileFilter = new WildcardFileFilter(storedIndex + ".*");
		File[] files = dir.listFiles(fileFilter);
		for (File file : files) {
			file.delete();
		}
	}

	public boolean saveFile(byte[] fileContent, Book book) throws IOException {
		String filePath = ebookStorage.getMainCatalogue() + book.getStoredIndex() + "." + book.getExtension();
		File file = new File(filePath);
		if (!file.exists()) {
			FileCopyUtils.copy(fileContent, new FileOutputStream(filePath));
			return false;
		}
		return true;
	}

	private void extractZip(String zipFile) throws IOException {
		int BUFFER = 2048;
		File file = new File(zipFile);

		ZipFile zip = new ZipFile(file);
		String newPath = zipFile.substring(0, zipFile.length() - 4);

		new File(newPath).mkdir();
		Enumeration zipFileEntries = zip.entries();

		// Process each entry
		while (zipFileEntries.hasMoreElements()) {
			// grab a zip file entry
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
			String currentEntry = entry.getName();
			File destFile = new File(newPath, currentEntry);
			//destFile = new File(newPath, destFile.getName());
			File destinationParent = destFile.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();

			if (!entry.isDirectory()) {
				BufferedInputStream is = new BufferedInputStream(zip
						.getInputStream(entry));
				int currentByte;
				// establish buffer for writing file
				byte data[] = new byte[BUFFER];

				// write the current file to disk
				FileOutputStream fos = new FileOutputStream(destFile);
				BufferedOutputStream dest = new BufferedOutputStream(fos,
						BUFFER);

				// read and write until last byte is encountered
				while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, currentByte);
				}
				dest.flush();
				dest.close();
				is.close();
			}

			if (currentEntry.endsWith(".zip")) {
				// found a zip file, try to open
				extractZip(destFile.getAbsolutePath());
			}
		}
		zip.close();
	}

}
