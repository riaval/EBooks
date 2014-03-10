package ua.miratech.zhukov.util.component.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.service.BookIndexerService;
import ua.miratech.zhukov.util.thread.IndexCallable;

@Component
public class IndexCallableFactory {

	@Autowired
	private BookIndexerService bookIndexerService;

	public IndexCallable createIndexCallable(IndexBook indexBook) {
		return new IndexCallable(indexBook, bookIndexerService);
	}

}
