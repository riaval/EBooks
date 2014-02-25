package ua.miratech.zhukov.service;

import org.apache.lucene.queryparser.classic.ParseException;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.dto.controller.SearchedBook;
import ua.miratech.zhukov.dto.output.Book;

import java.io.IOException;
import java.util.List;

public interface BookIndexerService {

	void indexFile(byte[] fileContent, Book book) throws IOException;

	public void doIndex(IndexBook book) throws IOException;

	public void deleteIndex(String fileName) throws IOException;

	public List<Long> doSimpleSearch(String content) throws IOException, ParseException;

	public List<Long> doExtendedSearch(SearchedBook searchedBook) throws IOException, ParseException;

}
