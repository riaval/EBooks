package ua.miratech.zhukov.mapper;

import ua.miratech.zhukov.dto.controller.EditedBook;
import ua.miratech.zhukov.dto.mapper.ShareInParam;
import ua.miratech.zhukov.dto.output.Book;
import ua.miratech.zhukov.dto.output.BookExt;

import java.util.*;

public class BookMapperMock implements BookMapper {

	@Override
	public List<Book> getLastBooks(int pageNumber, int pageSize, String currentUserEmail) {
		return null;
	}

	@Override
	public List<Book> getMyBooks(String userEmail, String orderBy) {
		return null;
	}

	@Override
	public Book getBookById(Long id) {
		Book book = new Book(
				"author",
				"title",
				Calendar.getInstance().getTime(),
				"fileName",
				(long) 100500,
				"sha1",
				"owner",
				"language",
				"extension",
				"annotation",
				"isbn",
				"sharedType",
				new ArrayList<>(Arrays.asList(new String[]{"epic", "fantasy"}))
		);
		return book;
	}

	@Override
	public Book getBookForUserById(String userEmail, Long bookId) {
		return null;
	}

	@Override
	public List<BookExt> getBooksByStoredIndex(String userEmail, List<Long> storedIndexes) {
		return null;
	}

	@Override
	public Long countBookByStoredIndex(Long storedIndex) {
		return null;
	}

	@Override
	public void add(Book book) {
		System.out.println("boooook");
	}

	@Override
	public int delete(String userEmail, Long bookId) {
		return 0;
	}

	@Override
	public void addGenre(Long userId, String genreName) {

	}

	@Override
	public void updateBook(EditedBook editedBook) {

	}

	@Override
	public int setSharedType(String userEmail, Long bookId, String sharedType) {
		return 0;
	}

	@Override
	public void share(ShareInParam shareInParam) {

	}

	@Override
	public void unShareBook(Long bookId, String ownerEmail, Long granteeID) {

	}

}
