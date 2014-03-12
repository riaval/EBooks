package ua.miratech.zhukov.service;

import org.apache.lucene.queryparser.classic.ParseException;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.dto.controller.SearchedBook;

import java.io.IOException;
import java.util.List;

public interface BookIndexerService {

	/**
	 * Creates full-text index for book
	 * This method creates new thread.
	 *
	 * @param fileContent book content
	 * @param book {@link ua.miratech.zhukov.dto.IndexBook} Book that contains necessary fields
	 * @throws IOException
	 */
	void createIndex(byte[] fileContent, Book book) throws IOException;

	/**
	 * Creates full-text index for book
	 *
	 * @param book {@link ua.miratech.zhukov.dto.IndexBook} Book that contains necessary fields
	 * @throws IOException
	 */
	public void createIndex(IndexBook book) throws IOException;

	/**
	 * Removes index from Storage
	 *
	 * @param fileName Name of file that must be removed
	 * @throws IOException
	 */
	public void deleteIndex(String fileName) throws IOException;

	/**
	 * Full-text search without additional parameters
	 *
	 * @param content Full-text argument
	 * @return List of storedIndex-es from book
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<String> doSimpleSearch(String content) throws IOException, ParseException;

	/**
	 * Full-text search with additional parameters
	 *
	 * @param searchedBook DTO that contains Full-text argument and additional parameters
	 * @return List of storedIndex-es from book
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<String> doExtendedSearch(SearchedBook searchedBook) throws IOException, ParseException;

}
