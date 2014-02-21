package ua.miratech.zhukov.mapper;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.Book;
import ua.miratech.zhukov.dto.controller.EditedBookInParam;
import ua.miratech.zhukov.dto.mapper.ShareInParam;

import java.util.List;

public interface BookMapper {

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

	public Book getBookForReadingById(
			  @Param("userEmail") String userEmail
			, @Param("bookId") Long bookId
	);

	public List<Book> getBooksByStoredIndex(
			  @Param("storedIndex") Long storedIndex
	);

	public Long countBookByStoredIndex(
			  @Param("storedIndex") Long storedIndex
	);

	public void add(Book book);

	public int delete(
			  @Param("userEmail") String userEmail
			, @Param("bookId") Long bookId);

	public void addGenre(
			  @Param("userId") Long userId
			, @Param("genreName") String genreName
	);

	public void updateBook(EditedBookInParam editedBookInParam);

	public int setSharedType(
			  @Param("userEmail") String userEmail
			, @Param("bookId") Long bookId
			, @Param("sharedType") String sharedType
	);

	public void share(ShareInParam shareInParam);

	public void unShareBook(
			  @Param("bookId") Long bookId
			, @Param("ownerEmail") String ownerEmail
			, @Param("granteeID") Long granteeID
	);

}

