package ua.miratech.zhukov.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.miratech.zhukov.dto.Book;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.mapper.BookIndexer;
import ua.miratech.zhukov.mapper.BookMapper;
import ua.miratech.zhukov.util.FictionBookParser;
import ua.miratech.zhukov.util.IndexCallable;
import ua.miratech.zhukov.util.UnCompressCallable;
import ua.miratech.zhukov.util.UploadedFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class FileService {

	@Autowired(required = false)
	private BookMapper bookMapper;

	@Autowired
	SecurityService securityService;

	@Autowired()
	@Qualifier("executorService")
	private ExecutorService service;

	@Autowired
	@Qualifier("bookIndexerImpl")
	private BookIndexer bookIndexer;

	private static final String FILE_DIRECTORY = "D:/EBOOKS_STORAGE/MAIN_CATALOGUE/";
	private static final String TEMP_DIRECTORY = "D:/EBOOKS_STORAGE/TEMP_FILES/";
	private static final String DEFAULT_SHARED_TYPE = "PRIVATE";
	private static final int MAX_FILE_SIZE = 0x1800000;

	public FileSystemResource uploadFile(Long bookId, HttpServletResponse response) {
		Book book = bookMapper.getBookById(bookId);

		String filePath = book.getPath() + book.getId() + "." + book.getExtension();
		response.setHeader("content-Disposition", "attachment; filename=" + book.getFileName());

		return new FileSystemResource(filePath);
	}

	//	TODO Transaction not working
	public List<UploadedFile> uploadFile(Map<String, MultipartFile> filesMap) throws IOException {
		LinkedList<UploadedFile> files = new LinkedList<>();

		for (String fileName : filesMap.keySet()) {
			MultipartFile mpf = filesMap.get(fileName);

			UploadedFile uploadedFile = new UploadedFile(
					mpf.getBytes(),
					mpf.getOriginalFilename(),
					mpf.getSize(),
					mpf.getContentType()
			);

			String userEmail = securityService.getUserEmail();

			if ("application/zip".equals(mpf.getContentType())) {
				service.submit(new UnCompressCallable(this, uploadedFile, userEmail));
			} else {
				uploadSimpleFile(uploadedFile, userEmail);
			}

//			if (mpf.getSize() > MAX_FILE_SIZE) {
//				continue;
//			}

			files.add(uploadedFile);
		}

		return files;
	}

	public UploadedFile uploadSimpleFile(UploadedFile uf, String userEmail) throws IOException {
		// Insert book to database
		Book book = insertBook(uf.getName(), uf.getSize(), uf.getBytes(), userEmail);

		// Save file to the Hard Drive
		saveFile(uf.getBytes(), book);

		// Index file
		indexFile(uf.getBytes(), book);

		uf.setType("text");

		return uf;
	}

	public List<UploadedFile> uploadZipFile(UploadedFile uf, String userEmail) throws IOException {
		List<UploadedFile> uploadedFiles = new ArrayList<>();

		Long systemTime = Calendar.getInstance().getTime().getTime();
		String zipDirPath = TEMP_DIRECTORY + systemTime;
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

			uploadedFiles.add(uploadSimpleFile(uploadedFile, userEmail));
		}

		FileUtils.deleteDirectory(zipDir);
		zipFile.delete();
		return uploadedFiles;
	}

	public void deleteFile(Long id) {
		File dir = new File(FILE_DIRECTORY);
		FileFilter fileFilter = new WildcardFileFilter(id + ".*");
		File[] files = dir.listFiles(fileFilter);
		for (File file : files) {
			file.delete();
		}
	}

	private Book insertBook(String fileName, Long fileSize, byte[] fileContent, String userEmail) {
//		System.out.println(securityService);
//
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		String userEmail = auth.getName();
		FictionBookParser fbp = new FictionBookParser(fileContent);
		Book book = new Book(
				fbp.getAuthor(),
				fbp.getTitle(),
				Calendar.getInstance().getTime(),
				fileName,
				fileSize,
				FILE_DIRECTORY,
				Integer.toString(Arrays.hashCode(fileContent)),
				userEmail,
				fbp.getLanguage(),
				FilenameUtils.getExtension(fileName),
				fbp.getAnnotation(),
				fbp.getISBN(),
				DEFAULT_SHARED_TYPE,
				fbp.getGenres()
		);
		bookMapper.add(book);
		for (String each : fbp.getGenres()) {
			bookMapper.addGenre(book.getId(), each);
		}

		return book;
	}

	private void saveFile(byte[] fileContent, Book book) throws IOException {
		String filePath = FILE_DIRECTORY + book.getId() + "." + book.getExtension();
		FileCopyUtils.copy(fileContent, new FileOutputStream(filePath));
	}

	private void indexFile(byte[] fileContent, Book book) throws IOException {
		IndexBook indexBook = new IndexBook(
				book.getAuthor(),
				book.getTitle(),
				book.getPublicationDate(),
				book.getSize(),
				book.getPath() + book.getId() + "." + book.getExtension(),
				book.getLanguage(),
				book.getAnnotation(),
				book.getIsbn(),
				book.getGenres(),
				fileContent
		);

		service.submit(new IndexCallable(indexBook, bookIndexer));
	}

//	private void unZipFile(MultipartFile mpf) throws IOException {
//		Long systemTime = Calendar.getInstance().getTime().getTime();
//		String zipDirPath = TEMP_DIRECTORY + systemTime;
//		String zipFilePath = zipDirPath + "." + FilenameUtils.getExtension(mpf.getOriginalFilename());
//
//		File zipDir = new File(zipDirPath);
//		File zipFile = new File(zipFilePath);
//
//		FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(zipFilePath));
//		extractZip(zipFilePath);
//
//		Collection files = FileUtils.listFiles(zipDir, new String[]{"fb2"}, true);
//		for (Object each : files) {
//			File file = (File) each;
//		}
//
//		FileUtils.deleteDirectory(zipDir);
//		zipFile.delete();
//	}

	private void extractZip(String zipFile) throws IOException {
		System.out.println(zipFile);
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
