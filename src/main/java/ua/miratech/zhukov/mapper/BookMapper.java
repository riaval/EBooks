package ua.miratech.zhukov.mapper;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.Book;

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

	public void add(Book book);

	public void delete(Long bookId);

	public void addGenre(
			  @Param("userId") Long userId
			, @Param("genreName") String genreName
	);

	public void setSharedType(
			  @Param("bookId") Long bookId
			, @Param("sharedType") String sharedType
	);

	public void share(
			  @Param("bookId") Long bookId
			, @Param("owner") String owner
			, @Param("grantee") String grantee
	);

	public void unShareBook(
			  @Param("bookId") Long bookId
			, @Param("ownerEmail") String ownerEmail
			, @Param("granteeID") Long granteeID);

}
