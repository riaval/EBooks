package ua.miratech.zhukov.service;

import org.springframework.data.domain.Page;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.dto.controller.EditedBook;
import ua.miratech.zhukov.dto.controller.SearchedBook;
import ua.miratech.zhukov.dto.enums.SharedType;
import ua.miratech.zhukov.dto.output.DownloadBook;
import ua.miratech.zhukov.dto.output.ReadingBook;

import java.io.IOException;
import java.util.List;

public interface BookService {

	/**
	 * Set Shared Type for Book.
	 * Based on {@link ua.miratech.zhukov.dto.enums.SharedType} sharedType-s enum
	 *
	 * @param bookId Book id
	 * @param sharedType The type described in {@link ua.miratech.zhukov.dto.enums.SharedType} SharedType
	 */
	void setSharedType(String bookId, SharedType sharedType);

	/**
	 * Share book to another user
	 *
	 * @param bookId Owner id
	 * @param granteeEmail Grantee User email
	 */
	void shareBook(String bookId, String granteeEmail);

	/**
	 * Remove sharing from book
	 *
	 * @param bookId Book id
	 * @param granteeId Grantee User id
	 */
	void unShareBook(String bookId, String granteeId);

	/**
	 * Return List of {@link org.springframework.data.domain.Page}
	 * with Books that belong to current User
	 *
	 * @param pageNumber Page number
	 * @return List of {@link org.springframework.data.domain.Page}
	 */
	Page<Book> getMyBooks(int pageNumber);

	/**
	 * Return List of {@link org.springframework.data.domain.Page}
	 * with latest Books
	 *
	 * @param pageNumber Page number
	 * @return List of {@link org.springframework.data.domain.Page}
	 */
	Page<Book> getLastBooks(int pageNumber);

	/**
	 * Remove the Book
	 *
	 * @param bookId Book id
	 * @throws IOException
	 */
	void deleteBook(String bookId) throws IOException;

	/**
	 * Update the Book
	 *
	 * @param book {@link ua.miratech.zhukov.dto.controller.EditedBook} that contains
	 *                                                                    necessary field
	 */
	void updateBook(EditedBook book);

	/**
	 * Add Book to service
	 *
	 * @param uf Uploaded file
	 * @param user {@link ua.miratech.zhukov.domain.User}, owner
	 * @return Created ID
	 * @throws IOException
	 */
	String addBook(UploadedFile uf, User user) throws IOException;

	/**
	 * Return Book with content for reading
	 *
	 * @param bookId Book id
	 * @return {@link ua.miratech.zhukov.dto.output.ReadingBook} with Book content
	 */
	ReadingBook getReadingBookById(String bookId);

	/**
	 * Return Book entity from DMS
	 *
	 * @param bookId Book id
	 * @return {@link ua.miratech.zhukov.domain.Book} entity
	 */
	Book getBookForEditing(String bookId);

	/**
	 * Return Book with fields for downloading
	 *
	 * @param bookId Book id
	 * @return DTO {@link ua.miratech.zhukov.dto.output.DownloadBook} with fields for downloading
	 */
	DownloadBook downloadBook(String bookId);

	/**
	 * Search Books by full-text content
	 *
	 * @param content Full-text search argument
	 * @param pageNumber Page number
	 * @return Pages with Books
	 */
	Page<Book> doSimpleSearch(String content, int pageNumber);

	/**
	 * Search Books by full-text content and additional parameters
	 *
	 * @param searchedBook DTO with content and additional parameters
	 * @param pageNumber Page number
	 * @return Pages with Books
	 */
	Page<Book> doExtendedSearch(SearchedBook searchedBook, int pageNumber);
}
