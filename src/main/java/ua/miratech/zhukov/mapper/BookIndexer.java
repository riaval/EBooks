package ua.miratech.zhukov.mapper;

import org.apache.lucene.queryparser.classic.ParseException;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.dto.SearchBook;

import java.io.IOException;
import java.util.List;

public interface BookIndexer {

	public void doIndex(IndexBook book) throws IOException;

	public List<Long> doSimpleSearch(String content) throws IOException, ParseException;

	public List<Long> doExtendedSearch(SearchBook searchBook) throws IOException, ParseException;

}
