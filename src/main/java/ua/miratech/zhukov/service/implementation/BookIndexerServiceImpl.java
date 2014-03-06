package ua.miratech.zhukov.service.implementation;

import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.dto.controller.SearchedBook;
import ua.miratech.zhukov.service.BookIndexerService;
import ua.miratech.zhukov.util.component.EbookStorage;
import ua.miratech.zhukov.util.thread.IndexCallable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class BookIndexerServiceImpl implements BookIndexerService {

	@Value("${fileName_term}")
	private String fileNameTerm;
	@Value("${path_term}")
	private String pathTerm;

	@Value("${content}")
	private String content;
	@Value("${author}")
	private String author;
	@Value("${title}")
	private String title;
	@Value("${language}")
	private String language;
	@Value("${genre}")
	private String genre;

	@Autowired
	private Version luceneVersion;

	@Autowired
	private EbookStorage ebookStorage;

	@Autowired
	@Qualifier("executorService")
	private ExecutorService service;

	@Override
	public void indexFile(byte[] fileContent, Book book) throws IOException {
		IndexBook indexBook = new IndexBook(
				book.getAuthor(),
				book.getTitle(),
				book.getPublicationDate(),
				book.getFileSize(),
				book.getStoredIndex() + "." + book.getExtension(),
				book.getLanguage(),
				book.getAnnotation(),
				book.getIsbn(),
				book.getGenres(),
				fileContent
		);

		service.submit(new IndexCallable(indexBook, this));
	}

	@Override
	public synchronized void createIndex(IndexBook book) throws IOException {
		solveIndex(book, null);
	}

	@Override
	public synchronized void deleteIndex(String fileName) throws IOException {
		solveIndex(null, fileName);
	}

	private synchronized void solveIndex(IndexBook book, String fileName) throws IOException {
		Analyzer analyzer = new StandardAnalyzer(luceneVersion);
		IndexWriterConfig iwc = new IndexWriterConfig(luceneVersion, analyzer);
		Directory dir = FSDirectory.open(new File(ebookStorage.getIndexCatalogue()));
		IndexWriter writer = new IndexWriter(dir, iwc);

		if (book == null) { 	// Delete index
			writer.deleteDocuments(new Term(fileNameTerm, fileName));
		} else {				// Create index
			createIndex(writer, book);
		}
		writer.close();
		dir.close();
	}

	@Override
	public List<String> doSimpleSearch(String content) throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer(luceneVersion);

		if (content.isEmpty()) {
			return null;
		}

		Query contentQuery = new QueryParser(luceneVersion, this.content, analyzer).parse(content);

		return doSearch(contentQuery);
	}

	@Override
	public List<String> doExtendedSearch(SearchedBook book) throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer(luceneVersion);
		BooleanQuery booleanQuery = new BooleanQuery();

		if (!book.getContent().isEmpty()) {
			Query contentQuery = new QueryParser(luceneVersion, content, analyzer).parse(book.getContent());
			booleanQuery.add(contentQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getTitle().isEmpty()) {
			Query titleQuery = new QueryParser(luceneVersion, title, analyzer).parse(book.getTitle());
			booleanQuery.add(titleQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getAuthor().isEmpty()) {
			Query authorQuery = new QueryParser(luceneVersion, author, analyzer).parse(book.getAuthor());
			booleanQuery.add(authorQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getLanguage().isEmpty()) {
			Query languageQuery = new QueryParser(luceneVersion, language, analyzer).parse(book.getLanguage());
			booleanQuery.add(languageQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getGenre().isEmpty()) {
			Query genreQuery = new QueryParser(luceneVersion, genre, analyzer).parse(book.getGenre());
			booleanQuery.add(genreQuery, BooleanClause.Occur.MUST);
		}

		return doSearch(booleanQuery);
	}

	private List<String> doSearch (Query query) throws IOException {
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(ebookStorage.getIndexCatalogue())));
		IndexSearcher searcher = new IndexSearcher(reader);

		TopDocs results = searcher.search(query, 10);
		ScoreDoc[] hits = results.scoreDocs;
		List<String> storedIndexes = new ArrayList<>();
		for (ScoreDoc hit : hits) {
			Document doc = searcher.doc(hit.doc);
			String fileName = doc.get(fileNameTerm);
			String storedIndex = FilenameUtils.removeExtension(fileName);
			storedIndexes.add(storedIndex);
		}
		reader.close();

		return storedIndexes;
	}

	private void createIndex(IndexWriter writer, IndexBook book) throws IOException {
		Document doc = new Document();
		Field pathField = new StringField(fileNameTerm, book.getFileName(), Field.Store.YES);
		doc.add(pathField);

		doc.add(new TextField(content, new String(book.getContent()), Field.Store.YES));
		doc.add(new TextField(title, book.getTitle(), Field.Store.YES));
		doc.add(new TextField(author, book.getAuthor(), Field.Store.YES));
		doc.add(new TextField(language, book.getLanguage(), Field.Store.YES));
		for (String each : book.getGenres()) {
			doc.add(new TextField(genre, each, Field.Store.YES));
		}

		if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
			writer.addDocument(doc);
		} else {
			writer.updateDocument(new Term(pathTerm, book.getFileName()), doc);
		}
	}

}
