package ua.miratech.zhukov.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.miratech.zhukov.mapper.BookMapper;
import ua.miratech.zhukov.dto.Book;
import ua.miratech.zhukov.util.FictionBookParser;
import ua.miratech.zhukov.util.UploadedFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Map;

@Service
public class FileService {

	@Autowired(required = false)
	private BookMapper bookMapper;

	@Autowired
	private JobsService jobsService;

	private static final String FILE_DIRECTORY = "D:/EBOOKS_STORAGE/MAIN_CATALOGUE/";
	private static final String DEFAULT_SHARED_TYPE = "PRIVATE";

	public FileSystemResource downloadFile(Long bookId, HttpServletResponse response) {
		Book book = bookMapper.getBookById(bookId);

		String filePath = book.getPath() + book.getId() + "." + book.getExtension();
		response.setHeader("content-Disposition", "attachment; filename=" + book.getFileName());

		return new FileSystemResource(filePath);
	}

	public UploadedFile.UploadedFiles uploadFile(Map<String, MultipartFile> filesMap) {
		LinkedList<UploadedFile> files = new LinkedList<>();

		for (String fileName : filesMap.keySet()) {
			MultipartFile mpf = filesMap.get(fileName);
//			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());

			//If files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// Create new uploadedFile
			UploadedFile uploadedFile = new UploadedFile();
			uploadedFile.setName(mpf.getOriginalFilename());
			uploadedFile.setSize(mpf.getSize());
			uploadedFile.setType(mpf.getContentType());
			try {
				uploadedFile.setBytes(mpf.getBytes());
				long generatedId = insert(uploadedFile);
				String filePath = FILE_DIRECTORY + generatedId + "." + FilenameUtils.getExtension(uploadedFile.getName());

				// Write file to hard drive
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filePath));

				// Index file
				jobsService.newIndexFileJob(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//2.4 add to files
			files.add(uploadedFile);
		}

		return new UploadedFile.UploadedFiles(files);
	}

	public void deleteFile(Long id) {
		File dir = new File(FILE_DIRECTORY);
		FileFilter fileFilter = new WildcardFileFilter(id + ".*");
		File[] files = dir.listFiles(fileFilter);
		for (File file : files) {
			file.delete();
		}
	}

	@Transactional // TODO transactions not working
	private Long insert(UploadedFile uploadedFile) {
		FictionBookParser fbp = new FictionBookParser(uploadedFile.getBytes());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		Book book = new Book(
				fbp.getAuthor(),
				fbp.getTitle(),
				Calendar.getInstance().getTime(),
				uploadedFile.getName(),
				uploadedFile.getSize(),
				FILE_DIRECTORY,
				Integer.toString(uploadedFile.hashCode()),
				userEmail,
				fbp.getLanguage(),
				FilenameUtils.getExtension(uploadedFile.getName()),
				fbp.getAnnotation(),
				fbp.getISBN(),
				DEFAULT_SHARED_TYPE,
				fbp.getGenres()
		);
		bookMapper.add(book);
//		int i=0;
		for (String each : fbp.getGenres()) {
//			if (i == 1 )
//				throw new RuntimeException();
			bookMapper.addGenre(book.getId(), each);
//			i++;
		}

		return book.getId();
	}

}
