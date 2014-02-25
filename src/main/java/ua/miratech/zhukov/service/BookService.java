package ua.miratech.zhukov.service;

import ua.miratech.zhukov.dto.controller.SearchedBook;
import ua.miratech.zhukov.dto.output.ReadingBook;
import ua.miratech.zhukov.dto.enums.SharedType;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.dto.controller.EditedBook;
import ua.miratech.zhukov.dto.output.Book;
import ua.miratech.zhukov.dto.output.DownloadBook;

import java.io.IOException;
import java.util.List;

public interface BookService {

	Long addBook(UploadedFile uploadedFile, String userEmail) throws IOException;

	ReadingBook getReadingBookById(Long bookId) throws IOException, SecurityException;

	void deleteBook(Long bookId) throws IOException;

	void setSharedType(Long bookId, SharedType sharedType);

	void shareBook(Long bookId, String email);

	void unShareBook(Long bookId, Long userId);

	DownloadBook downloadBook(Long bookId);

	Book getBookForEditing(Long bookId);

	void updateBook(EditedBook book);

	List<Book> getLastBooks();

	List<Book> getMyBooks();

	List<Book> doSimpleSearch(String content);

	List<Book> doExtendedSearch(SearchedBook searchedBook);
}
