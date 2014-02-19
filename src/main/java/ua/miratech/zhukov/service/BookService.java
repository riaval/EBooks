package ua.miratech.zhukov.service;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import ua.miratech.zhukov.dto.*;
import ua.miratech.zhukov.dto.mapper.ShareInParam;
import ua.miratech.zhukov.mapper.BookIndexer;
import ua.miratech.zhukov.mapper.BookMapper;
import ua.miratech.zhukov.util.FictionBookParser;
import ua.miratech.zhukov.util.component.EbookStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

	@Autowired(required = false)
	private BookMapper bookMapper;

	@Autowired
	private FileService fileService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	@Qualifier("bookIndexerService")
	private BookIndexer bookIndexer;

	@Autowired
	EbookStorage ebookStorage;

	public ReadingBook getReadingBookById(Long bookId) throws IOException, SecurityException {
		String userEmail = securityService.getUserEmail();
		Book book = bookMapper.getBookForReadingById(userEmail, bookId);

		if (book == null) {
			// TODO logging
			throw new SecurityException(
					"User [email:" + userEmail + "] is not authorized to read book [id:" + bookId + "]");
		}

		File file = new File(ebookStorage.getMainCatalogue() + book.getStoredIndex() + "." + book.getExtension());
		FileInputStream fis = null;
		NodeList content = null;
		try {
			fis = new FileInputStream(file);
			byte[] bytes = IOUtils.toByteArray(fis);
			FictionBookParser fbp = new FictionBookParser(bytes);
			content = fbp.getContent();
		} catch (IOException e) {
			// TODO Add Logging
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return new ReadingBook(book, content);
	}

	public List<Book> getMyBooks() {
		String userEmail = securityService.getUserEmail();

		return bookMapper.getMyBooks(userEmail, "DESC");
	}

	public List<Book> getLastBooks() {
		String userEmail = securityService.getUserEmail();
		int currentPage = 1;
		int lastBooksAmount = 10;

		return bookMapper.getLastBooks(currentPage, lastBooksAmount, userEmail);
	}

	public void deleteBook(Long bookId) {
		String userEmail = securityService.getUserEmail();

		Book book = bookMapper.getBookById(bookId);

		if (book == null) {
			// TODO logging
			throw new IllegalArgumentException("Book [id:" + bookId + "] not fount for deleting");
		}

		int deletedBooks = bookMapper.delete(userEmail, bookId);

		if (deletedBooks == 0) {
			// TODO logging
			throw new SecurityException(
					"User [email:" + userEmail + "] is not authorized to delete book [id:" + bookId + "]");
		}

		Long storedIndex = book.getStoredIndex();
		Long count = bookMapper.countBookByStoredIndex(storedIndex);
		System.out.println(count);
		if (count == 0) {
			fileService.deleteFile(book.getStoredIndex());
			// TODO delete index
		}

	}

	public void setSharedType(Long bookId, SharedType sharedType) {
		String userEmail = securityService.getUserEmail();

		int updatedBooks = bookMapper.setSharedType(userEmail, bookId, sharedType.name());

		if (updatedBooks == 0) {
			// TODO logging
			throw new SecurityException(
					"User [email:" + userEmail + "] is not authorized to change shared type book [id:" + bookId + "]");
		}
	}

	public void shareBook(Long bookId, String granteeEmail) {
		String ownerEmail = securityService.getUserEmail();
		ShareInParam shareInParam = new ShareInParam(bookId, ownerEmail, granteeEmail);
		bookMapper.share(shareInParam);
		switch (shareInParam.getResultStatus()) {
			case 0: throw new IllegalArgumentException("Already shared");
			case -1: throw new IllegalArgumentException("User owner not found");
			case -2: throw new IllegalArgumentException("User grantee not found");
			case -3: throw new IllegalArgumentException("Book not found");
			case -4: throw new IllegalArgumentException("Owner and grantee is the same user");
			case -5: throw new SecurityException("User does not have required permissions");
		}
	}

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

	public List<Book> doSimpleSearch(String content) throws IOException, ParseException {
		List<Long> storedIndexes = bookIndexer.doSimpleSearch(content);
		return getBooksFromStoredIndexes(storedIndexes);
	}

	public List<Book> doExtendedSearch(SearchBook searchBook) throws IOException, ParseException {
		List<Long> storedIndexes = bookIndexer.doExtendedSearch(searchBook);
		return getBooksFromStoredIndexes(storedIndexes);
	}

	private List<Book> getBooksFromStoredIndexes(List<Long> storedIndexes) {
		List<Book> books = new ArrayList<>();
		for (Long each : storedIndexes) {
			books.addAll(bookMapper.getBooksByStoredIndex(each));
		}
		return books;
	}

}
