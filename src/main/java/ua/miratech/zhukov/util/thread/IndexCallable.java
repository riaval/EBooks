package ua.miratech.zhukov.util.thread;

import org.apache.log4j.Logger;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.service.BookIndexerService;

import java.io.IOException;
import java.util.concurrent.Callable;

public class IndexCallable implements Callable<Boolean> {

	private static final Logger logger = Logger.getLogger(IndexCallable.class);

	private IndexBook book;
	private BookIndexerService bookIndexerService;

	public IndexCallable(IndexBook book, BookIndexerService bookIndexerService) {
		this.book = book;
		this.bookIndexerService = bookIndexerService;
	}

	@Override
	public Boolean call() throws IOException {
		logger.info("-> Thread with book indexing [file_name:" + book.getFileName() + "] started");
		bookIndexerService.doIndex(book);
		logger.info("<- Thread with book indexing [file_name:" + book.getFileName() + "] finished");
		return true;
	}
}
