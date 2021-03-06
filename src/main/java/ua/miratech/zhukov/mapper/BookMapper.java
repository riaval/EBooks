package ua.miratech.zhukov.mapper;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.controller.EditedBook;
import ua.miratech.zhukov.dto.output.Book;
import ua.miratech.zhukov.dto.mapper.ShareInParam;
import ua.miratech.zhukov.dto.output.BookExt;

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

	public Book getBookForUserById(
			  @Param("userEmail") String userEmail
			, @Param("bookId") Long bookId
	);

	public List<BookExt> getBooksByStoredIndex(
			  @Param("userEmail")String userEmail
			, @Param("storedIndexes") List<Long> storedIndexes
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

	public void updateBook(EditedBook editedBook);

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

