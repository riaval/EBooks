package ua.miratech.zhukov.dao;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.Book;

import java.util.Date;
import java.util.List;

public interface BookDAO {

	public List<Book> getLastBooks(
			  @Param("pageNumber") int pageNumber
			, @Param("pageSize") int pageSize
			, @Param("currentUserEmail") String currentUserEmail
	);

	public List<Book> getMyBooks(
			  @Param("userEmail") String userEmail
			, @Param("orderBy") String orderBy
	);

	public Book getBookById(
			  @Param("id") Long id
	);

	public void addBook(Book book);

	public void deleteBook(Long bookId);

	public void addGenre(
			  @Param("userId") Long userId
			, @Param("genreName") String genreName
	);

	public void setSharedType(
			  @Param("bookId") Long bookId
			, @Param("sharedType") String sharedType);
	}
