package ua.miratech.zhukov.service.mongodb;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.dto.controller.EditedBook;
import ua.miratech.zhukov.dto.controller.SearchedBook;
import ua.miratech.zhukov.dto.enums.SharedType;
import ua.miratech.zhukov.dto.output.DownloadBook;
import ua.miratech.zhukov.dto.output.ReadingBook;
import ua.miratech.zhukov.repository.BookRepository;
import ua.miratech.zhukov.repository.UserRepository;
import ua.miratech.zhukov.service.*;
import ua.miratech.zhukov.util.FictionBookParser;
import ua.miratech.zhukov.util.component.EbookStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

	private static final String defaultSharedType = "PRIVATE";
	private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private EbookStorage ebookStorage;

	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Qualifier("bookIndexerServiceImpl")
	private BookIndexerService bookIndexerService;

	@Override
	public String addBook(UploadedFile uf, User user) throws IOException {
		String md5 = Integer.toString(Arrays.hashCode(uf.getBytes()));

		Book storedBook = bookRepository.findByMD5(md5);
		String storedIndex;
		if (storedBook == null) {
			storedIndex = UUID.randomUUID().toString();
		} else {
			storedIndex = storedBook.getStoredIndex();
		}

		// Insert book to database
		FictionBookParser fbp = new FictionBookParser(uf.getBytes());
		Book book = new Book(
				fbp.getAuthor(),
				fbp.getTitle(),
				fbp.getAnnotation(),
				fbp.getISBN(),
				fbp.getLanguage(),
				fbp.getGenres(),
				uf.getName(),
				FilenameUtils.getExtension(uf.getName()),
				uf.getSize(),
				md5,
				user,
				defaultSharedType,
				storedIndex,
				Calendar.getInstance().getTime(),
				new ArrayList<User>()
		);
		bookRepository.save(book);

		if (storedBook == null) {
			// Save file to the Hard Drive
			fileService.saveFile(uf.getBytes(), book);

			// Index file
			bookIndexerService.indexFile(uf.getBytes(), book);
		}

		return book.getId();
	}

	@Override
	public ReadingBook getReadingBookById(String bookId) {
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
	public Book getBookForEditing(String bookId) {
		return checkReadPermissions(bookId);
	}

	@Override
	public List<Book> getMyBooks() {
		User user = userService.getCurrentUser();

		return bookRepository.findBooksByOwner( new ObjectId(user.getId()) );
	}

	@Override
	public List<Book> getLastBooks() {
		User user = userService.getCurrentUser();

		return bookRepository.findLastBooks();
	}

	@Override
	public void deleteBook(String bookId) throws IOException {
		Book book = checkWritePermissions(bookId);

		// Deleting from database
		bookRepository.delete(book);
		// Deleting from storage
		fileService.deleteFile(book.getStoredIndex());
		// Deleting file index
		bookIndexerService.deleteIndex(book.getStoredIndex() + "." + book.getExtension());
	}

	@Override
	public void updateBook(EditedBook editedBook) {
		Book book = checkWritePermissions(editedBook.getId());

		String[] genres = editedBook.getGenres().split(" ");

		book.setTitle(editedBook.getTitle());
		book.setAuthor(editedBook.getAuthor());
		book.setIsbn(editedBook.getIsbn());
		book.setLanguage(editedBook.getLanguage());
		book.setAnnotation(editedBook.getAnnotation());
		book.setGenres(Arrays.asList(genres));

		bookRepository.save(book);
	}

	@Override
	public void setSharedType(String bookId, SharedType sharedType) {
		Book book = checkWritePermissions(bookId);

		book.setSharedType(sharedType.toString());

		bookRepository.save(book);
	}

	@Override
	public void shareBook(String bookId, String granteeEmail) {
		Book book = checkWritePermissions(bookId);
		User granteeUser = userRepository.findByEmail(granteeEmail);

		if (granteeUser == null) {
			throw new IllegalArgumentException("Invited user [email:" + granteeEmail + "] not found");
		}

		List<User> users = book.getSharedFor();
		users.add(granteeUser);

		bookRepository.save(book);
//		switch (shareInParam.getResultStatus()) {
//			case 0:
//				throw new IllegalArgumentException("Already shared");
//			case -1:
//				throw new IllegalArgumentException("User owner not found");
//			case -2:
//
//			case -3:
//				throw new IllegalArgumentException("Book not found");
//			case -4:
//				throw new IllegalArgumentException("Owner and grantee is the same user");
//			case -5:
//				throw new SecurityException("User does not have required permissions");
//		}
	}

	@Override
	public void unShareBook(String bookId, String granteeId) {
		Book book = checkWritePermissions(bookId);
		User granteeUser = userRepository.findOne(granteeId);

		if (granteeUser == null) {
			throw new IllegalArgumentException("Invited user [id:" + granteeId + "] not found");
		}

		List<User> users = book.getSharedFor();
		users.remove(granteeUser);

		bookRepository.save(book);
	}

	@Override
	public DownloadBook downloadBook(String bookId) {
		Book book = checkReadPermissions(bookId);

		return fileService.getDownloadBook(book);
	}

	@Override
	public List<Book> doSimpleSearch(String content) {
		List<String> storedIndexes;
		try {
			storedIndexes = bookIndexerService.doSimpleSearch(content);
		} catch (IOException | ParseException e) {
			logger.error(e);
			throw new RuntimeException(e.getMessage());
		}

		return getBooksFromStoredIndexes(storedIndexes);
	}

	@Override
	@Secured({"ROLE_USER"})
	public List<Book> doExtendedSearch(SearchedBook searchedBook) {
		List<String> storedIndexes = null;
		try {
			storedIndexes = bookIndexerService.doExtendedSearch(searchedBook);
		} catch (IOException | ParseException e) {
			logger.error(e);
			throw new RuntimeException(e.getMessage());
		}

		return getBooksFromStoredIndexes(storedIndexes);
	}

	private List<Book> getBooksFromStoredIndexes(List<String> storedIndexes) {
		if (storedIndexes.size() == 0) {
			return null;
		}
		User user = userService.getCurrentUser();

		return bookRepository.findBooksWithStoredIndexes(new ObjectId(user.getId()), storedIndexes);
	}

	private Book checkWritePermissions(String bookId) {
		Book book = findBook(bookId);

		User user = userService.getCurrentUser();
		if (!book.getOwner().equals(user)) {
			throw new SecurityException(
					"Cannot get book [id:" + bookId + "] for User [email:" + user.getEmail() +
					"] with 'write' permissions");
		}

		return book;
	}

	private Book checkReadPermissions(String bookId) {
		Book book = findBook(bookId);

		User user = userService.getCurrentUser();

		boolean isPublic = book.getSharedType().equals("PUBLIC");
		boolean isOwner = book.getOwner().equals(user);
		boolean isBookShared = book.getSharedFor().contains(user);
		if (!isPublic && !isOwner && !isBookShared) {
			throw new SecurityException(
					"Cannot get book [id:" + bookId + "] for User [email:" + user.getEmail() +
					"] with 'read' permissions");
		}

		return book;
	}

	private Book findBook(String bookId) {
		Book book = bookRepository.findOne(bookId);
		if (book == null) {
			throw new IllegalArgumentException(
					"Book [id:" + bookId + "] not found");
		}

		return book;
	}

}
