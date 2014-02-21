package ua.miratech.zhukov.util.thread;

import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.service.BookIndexerService;

import java.io.IOException;
import java.util.concurrent.Callable;

public class IndexCallable implements Callable<Boolean> {

	private IndexBook book;
	private BookIndexerService bookIndexerService;

	public IndexCallable(IndexBook book, BookIndexerService bookIndexerService) {
		this.book = book;
		this.bookIndexerService = bookIndexerService;
	}

	@Override
	public Boolean call() throws IOException {
		// TODO logging
		bookIndexerService.doIndex(book);
		return true;
	}
}
