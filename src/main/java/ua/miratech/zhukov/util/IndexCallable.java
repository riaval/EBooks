package ua.miratech.zhukov.util;

import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.mapper.BookIndexer;

import java.io.IOException;
import java.util.concurrent.Callable;

public class IndexCallable implements Callable<Boolean> {

	private IndexBook book;
	private BookIndexer bookIndexer;

	public IndexCallable(IndexBook book, BookIndexer bookIndexer) {
		this.book = book;
		this.bookIndexer = bookIndexer;
	}

	@Override
	public Boolean call() throws IOException {
//		System.out.println("index starting");
		bookIndexer.doIndex(book);
//		System.out.println("index finish");
		return true;
	}
}
