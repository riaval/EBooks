package ua.miratech.zhukov.service;

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

	void setSharedType(String bookId, SharedType sharedType);

	void shareBook(String bookId, String granteeEmail);

	void unShareBook(String bookId, String granteeId);

	void deleteBook(String bookId) throws IOException;

	void updateBook(EditedBook book);

	List<Book> getLastBooks();

	String addBook(UploadedFile uf, User user) throws IOException;

	ReadingBook getReadingBookById(String bookId);

	Book getBookForEditing(String bookId);

	List<Book> getMyBooks();

	DownloadBook downloadBook(String bookId);

	List<Book> doSimpleSearch(String content);

	List<Book> doExtendedSearch(SearchedBook searchedBook);
}
