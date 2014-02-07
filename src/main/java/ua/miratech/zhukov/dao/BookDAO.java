package ua.miratech.zhukov.dao;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.Book;

import java.util.Date;
import java.util.List;

public interface BookDAO {

	public List<Book> getMyBooks(@Param("userEmail") String userEmail, @Param("orderBy") String orderBy);

	public Book getBookById(@Param("id") Long id);

	public void addBook(Book book);

	public void addGenre(@Param("userId") Long userId, @Param("genreName") String genreName);

}
