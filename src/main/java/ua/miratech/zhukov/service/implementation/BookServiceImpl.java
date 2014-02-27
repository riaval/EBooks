package ua.miratech.zhukov.service.implementation;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import ua.miratech.zhukov.dto.controller.SearchedBook;
import ua.miratech.zhukov.dto.output.BookExt;
import ua.miratech.zhukov.dto.output.ReadingBook;
import ua.miratech.zhukov.dto.enums.SharedType;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.dto.controller.EditedBook;
import ua.miratech.zhukov.dto.mapper.ShareInParam;
import ua.miratech.zhukov.dto.output.Book;
import ua.miratech.zhukov.dto.output.DownloadBook;
import ua.miratech.zhukov.mapper.BookMapper;
import ua.miratech.zhukov.service.BookIndexerService;
import ua.miratech.zhukov.service.BookService;
import ua.miratech.zhukov.service.FileService;
import ua.miratech.zhukov.service.SecurityService;
import ua.miratech.zhukov.util.FictionBookParser;
import ua.miratech.zhukov.util.component.EbookStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	private static final String defaultSharedType = "PRIVATE";
	private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

	@Autowired(required = false)
	private BookMapper bookMapper;

	@Autowired
	private EbookStorage ebookStorage;

	@Autowired
	private FileService fileService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	@Qualifier("bookIndexerServiceImpl")
	private BookIndexerService bookIndexerService;

	@Override
	public Long addBook(UploadedFile uf, String userEmail) throws IOException {
		// Insert book to database
		Book book = insertBook(uf.getName(), uf.getSize(), uf.getBytes(), userEmail);

		Long storedIndexes = bookMapper.countBookByStoredIndex(book.getStoredIndex());
		if (storedIndexes == 1) {
			// Save file to the Hard Drive
			fileService.saveFile(uf.getBytes(), book);

			// Index file
			bookIndexerService.indexFile(uf.getBytes(), book);
		}

		return book.getId();
	}

	@Override
	public ReadingBook getReadingBookById(Long bookId) {
		Book book = getBookForEditing(bookId);

		File file = new File(ebookStorage.getMainCatalogue() + book.getStoredIndex() + "." + book.getExtension());
		FileInputStream fis = null;
		NodeList content = null;
		try {
			fis = new FileInputStream(file);
			byte[] bytes = IOUtils.toByteArray(fis);
			FictionBookParser fbp = new FictionBookParser(bytes);
			content = fbp.getContent();
		} catch (IOException e) {
			logger.error(e);
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				logger.error(ex);
				throw new RuntimeException(ex.getMessage());
			}
		}

		return new ReadingBook(book, content);
	}

	@Override
	public Book getBookForEditing(Long bookId) {
		String userEmail = securityService.getUserEmail();
		Book book = bookMapper.getBookForUserById(userEmail, bookId);

		if (book == null) {
			throw new IllegalArgumentException(
					"Cannot get book [id:" + bookId + "] for User [email:" + userEmail + "]");
		}

		return book;
	}

	@Override
	public List<Book> getMyBooks() {
		String userEmail = securityService.getUserEmail();

		return bookMapper.getMyBooks(userEmail, "DESC");
	}

	@Override
	public List<Book> getLastBooks() {
		String userEmail = securityService.getUserEmail();
		int currentPage = 1;
		int lastBooksAmount = 10;

		return bookMapper.getLastBooks(currentPage, lastBooksAmount, userEmail);
	}

	@Override
	public void deleteBook(Long bookId) throws IOException {
		String userEmail = securityService.getUserEmail();

		Book book = bookMapper.getBookById(bookId);

		if (book == null) {
			throw new IllegalArgumentException(
					"Book [id:" + bookId + "] not fount for deleting");
		}

		Integer deletedBooks = bookMapper.delete(userEmail, bookId);

		if (deletedBooks == 0) {
			throw new IllegalArgumentException(
					"Cannot delete book [id:" + bookId + "] for User [email:" + userEmail + "]");
		}

		Long storedIndex = book.getStoredIndex();
		Long count = bookMapper.countBookByStoredIndex(storedIndex);

		if (count == 0) {
			fileService.deleteFile(book.getStoredIndex());
			bookIndexerService.deleteIndex(book.getStoredIndex() + "." + book.getExtension());
		}

	}

	@Override
	public void updateBook(EditedBook editedBook) {
		String userEmail = securityService.getUserEmail();

		editedBook.setUserEmail(userEmail);

		bookMapper.updateBook(editedBook);

		if (editedBook.getUpdatedCount() < 1) {
			throw new IllegalArgumentException(
					"Cannot update book [id:" + editedBook.getId() + "] for User [email:" + userEmail + "]");
		}
	}

	@Override
	public void setSharedType(Long bookId, SharedType sharedType) {
		String userEmail = securityService.getUserEmail();

		int updatedBooks = bookMapper.setSharedType(userEmail, bookId, sharedType.name());

		if (updatedBooks == 0) {
			throw new IllegalArgumentException("Cannot share book [id:" + bookId +
					"] for User [email:" + userEmail + "] with type [sharedType:" + sharedType + "]");
		}
	}

	@Override
	public void shareBook(Long bookId, String granteeEmail) {
		String ownerEmail = securityService.getUserEmail();
		ShareInParam shareInParam = new ShareInParam(bookId, ownerEmail, granteeEmail);
		bookMapper.share(shareInParam);
		switch (shareInParam.getResultStatus()) {
			case 0:
				throw new IllegalArgumentException("Already shared");
			case -1:
				throw new IllegalArgumentException("User owner not found");
			case -2:
				throw new IllegalArgumentException("User grantee not found");
			case -3:
				throw new IllegalArgumentException("Book not found");
			case -4:
				throw new IllegalArgumentException("Owner and grantee is the same user");
			case -5:
				throw new SecurityException("User does not have required permissions");
		}
	}

	@Override
	public void unShareBook(Long bookId, Long userId) {
		String ownerEmail = securityService.getUserEmail();
		Book book = bookMapper.getBookById(bookId);

		if (book == null) {
			throw new IllegalArgumentException("Book [id:" + bookId + "] not fount for unsharing");
		}

		if (!ownerEmail.equals(book.getOwner())) {
			throw new SecurityException(
					"User [email:" + ownerEmail + "] is not authorized to do this acton");
		}

		bookMapper.unShareBook(bookId, ownerEmail, userId);
	}

	@Override
	public DownloadBook downloadBook(Long bookId) {
		String userEmail = securityService.getUserEmail();

		Book book = bookMapper.getBookForUserById(userEmail, bookId);
		if (book == null) {
			throw new IllegalArgumentException(
					"Cannot get book [id:" + bookId + "] for User [email:" + userEmail + "]");
		}

		return fileService.getDownloadBook(book);
	}

	@Override
	public List<BookExt> doSimpleSearch(String content) {
		List<Long> storedIndexes;
		try {
			storedIndexes = bookIndexerService.doSimpleSearch(content);
		} catch (IOException | ParseException e) {
			logger.error(e);
			throw new RuntimeException(e.getMessage());
		}
		return getBooksFromStoredIndexes(storedIndexes);
	}

	@Override
	public List<BookExt> doExtendedSearch(SearchedBook searchedBook) {
		List<Long> storedIndexes = null;
		try {
			storedIndexes = bookIndexerService.doExtendedSearch(searchedBook);
		} catch (IOException | ParseException e) {
			logger.error(e);
			throw new RuntimeException(e.getMessage());
		}
		return getBooksFromStoredIndexes(storedIndexes);
	}

	private List<BookExt> getBooksFromStoredIndexes(List<Long> storedIndexes) {
		String userEmail = securityService.getUserEmail();
		if (storedIndexes.size() == 0) {
			return null;
		}
		return bookMapper.getBooksByStoredIndex(userEmail, storedIndexes);
	}

	private Book insertBook(String fileName, Long fileSize, byte[] fileContent, String userEmail) {
		FictionBookParser fbp = new FictionBookParser(fileContent);
		Book book = new Book(
				fbp.getAuthor(),
				fbp.getTitle(),
				Calendar.getInstance().getTime(),
				fileName,
				fileSize,
				Integer.toString(Arrays.hashCode(fileContent)),
				userEmail,
				fbp.getLanguage(),
				FilenameUtils.getExtension(fileName),
				fbp.getAnnotation(),
				fbp.getISBN(),
				defaultSharedType,
				fbp.getGenres()
		);
		bookMapper.add(book);
		Book insertedBook = bookMapper.getBookById(book.getId());

		for (String each : fbp.getGenres()) {
			bookMapper.addGenre(insertedBook.getId(), each);
		}

		return insertedBook;
	}

}
