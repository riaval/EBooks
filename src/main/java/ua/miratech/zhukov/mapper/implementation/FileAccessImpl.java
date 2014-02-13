package ua.miratech.zhukov.mapper.implementation;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import ua.miratech.zhukov.dto.Book;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Deprecated
@Component
public class FileAccessImpl {

	private static final String FILE_DIRECTORY = "D:/EBOOKS_STORAGE/MAIN_CATALOGUE/";

	public void saveFile(byte[] fileContent, Book book) throws IOException {
		String filePath = FILE_DIRECTORY + book.getId() + "." + book.getExtension();
		FileCopyUtils.copy(fileContent, new FileOutputStream(filePath));
	}

	public void deleteFile(Long id) {
		File dir = new File(FILE_DIRECTORY);
		FileFilter fileFilter = new WildcardFileFilter(id + ".*");
		File[] files = dir.listFiles(fileFilter);
		for (File file : files) {
			file.delete();
		}
	}

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
