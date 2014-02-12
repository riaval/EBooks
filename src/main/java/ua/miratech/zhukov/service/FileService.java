package ua.miratech.zhukov.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.mapper.BookIndexer;
import ua.miratech.zhukov.mapper.BookMapper;
import ua.miratech.zhukov.dto.Book;
import ua.miratech.zhukov.util.FictionBookParser;
import ua.miratech.zhukov.util.IndexCallable;
import ua.miratech.zhukov.util.UploadedFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;

@Service
public class FileService {

	@Autowired(required = false)
	private BookMapper bookMapper;

	@Autowired()
	@Qualifier("executorService")
	private ExecutorService service;

	@Autowired
	@Qualifier("bookIndexerImpl")
	private BookIndexer bookIndexer;

	private static final String FILE_DIRECTORY = "D:/EBOOKS_STORAGE/MAIN_CATALOGUE/";
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

			if (files.size() >= 50) {
				break;
			}

			if (mpf.getSize() > MAX_FILE_SIZE) {
				continue;
			}

			// Insert book to database
			Book book = insertBook(mpf);

			// Save file to the Hard Drive
			saveFile(mpf, book.getId());

			// Index file
			indexFile(mpf, book);

			UploadedFile uploadedFile = new UploadedFile(
					mpf.getBytes(),
					mpf.getOriginalFilename(),
					mpf.getSize(),
					mpf.getContentType()
			);
			files.add(uploadedFile);
		}

		return files;
	}

	public void deleteFile(Long id) {
		File dir = new File(FILE_DIRECTORY);
		FileFilter fileFilter = new WildcardFileFilter(id + ".*");
		File[] files = dir.listFiles(fileFilter);
		for (File file : files) {
			file.delete();
		}
	}

	private Book insertBook(MultipartFile mpf) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();

		String bookFileName = mpf.getOriginalFilename();
		Long bookSize = mpf.getSize();
		byte[] bookBytes = mpf.getBytes();

		FictionBookParser fbp = new FictionBookParser(bookBytes);
		Book book = new Book(
				fbp.getAuthor(),
				fbp.getTitle(),
				Calendar.getInstance().getTime(),
				bookFileName,
				bookSize,
				FILE_DIRECTORY,
				Integer.toString(Arrays.hashCode(bookBytes)),
				userEmail,
				fbp.getLanguage(),
				FilenameUtils.getExtension(bookFileName),
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

	private void saveFile(MultipartFile mpf, Long bookId) throws IOException {
		String filePath = FILE_DIRECTORY + bookId + "." + FilenameUtils.getExtension(mpf.getName());
		FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filePath));
	}

	private void indexFile(MultipartFile mpf, Book book) throws IOException {
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
				mpf.getBytes()
		);

		service.submit(new IndexCallable(indexBook, bookIndexer));
	}

//	@Deprecated
//	@Transactional // TODO transactions not working
//	private Long storeFile(UploadedFile uploadedFile) {
//		FictionBookParser fbp = new FictionBookParser(uploadedFile.getBytes());
//
//		String userEmail = auth.getName();
//		Book book = new Book(
//				fbp.getAuthor(),
//				fbp.getTitle(),
//				Calendar.getInstance().getTime(),
//				uploadedFile.getName(),
//				uploadedFile.getSize(),
//				FILE_DIRECTORY,
//				Integer.toString(uploadedFile.hashCode()),
//				userEmail,
//				fbp.getLanguage(),
//				FilenameUtils.getExtension(uploadedFile.getName()),
//				fbp.getAnnotation(),
//				fbp.getISBN(),
//				DEFAULT_SHARED_TYPE,
//				fbp.getGenres()
//		);
//		bookMapper.add(book);
//		for (String each : fbp.getGenres()) {
//			bookMapper.addGenre(book.getId(), each);
//		}
//
//		return book.getId();
//	}

}
