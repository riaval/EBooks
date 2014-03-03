package ua.miratech.zhukov.service.relational;

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

	private static final String content = "content";
	private static final String author = "author";
	private static final String title = "title";
	private static final String language = "language";
	private static final String genre = "genre";

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
	public synchronized void doIndex(IndexBook book) throws IOException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		Directory dir = FSDirectory.open(new File(ebookStorage.getIndexCatalogue()));

		IndexWriter writer = new IndexWriter(dir, iwc);
		createIndex(writer, book);
		writer.close();
		dir.close();
	}

	@Override
	public synchronized void deleteIndex(String fileName) throws IOException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		Directory dir = FSDirectory.open(new File(ebookStorage.getIndexCatalogue()));

		IndexWriter writer = new IndexWriter(dir, iwc);
		writer.deleteDocuments(new Term("fileName", fileName));
		writer.close();
		dir.close();
	}

	@Override
	public List<String> doSimpleSearch(String content) throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		if (content.isEmpty()) {
			return null;
		}

		Query contentQuery = new QueryParser(Version.LUCENE_46, BookIndexerServiceImpl.content, analyzer).parse(content);

		return doSearch(contentQuery);
	}

	@Override
	public List<String> doExtendedSearch(SearchedBook book) throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		BooleanQuery booleanQuery = new BooleanQuery();

		if (!book.getContent().isEmpty()) {
			Query contentQuery = new QueryParser(Version.LUCENE_46, content, analyzer).parse(book.getContent());
			booleanQuery.add(contentQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getTitle().isEmpty()) {
			Query titleQuery = new QueryParser(Version.LUCENE_46, title, analyzer).parse(book.getTitle());
			booleanQuery.add(titleQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getAuthor().isEmpty()) {
			Query authorQuery = new QueryParser(Version.LUCENE_46, author, analyzer).parse(book.getAuthor());
			booleanQuery.add(authorQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getLanguage().isEmpty()) {
			Query languageQuery = new QueryParser(Version.LUCENE_46, language, analyzer).parse(book.getLanguage());
			booleanQuery.add(languageQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getGenre().isEmpty()) {
			Query genreQuery = new QueryParser(Version.LUCENE_46, genre, analyzer).parse(book.getGenre());
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
			String fileName = doc.get("fileName");
			String storedIndex = FilenameUtils.removeExtension(fileName);
			storedIndexes.add(storedIndex);
		}
		reader.close();

		return storedIndexes;
	}

	private void createIndex(IndexWriter writer, IndexBook book) throws IOException {
		Document doc = new Document();
		Field pathField = new StringField("fileName", book.getFileName(), Field.Store.YES);
		doc.add(pathField);

		doc.add(new TextField(content, new String(book.getContent()), Field.Store.YES));
		doc.add(new TextField(title, book.getTitle(), Field.Store.YES));
		doc.add(new TextField(author, book.getAuthor(), Field.Store.YES));
		doc.add(new TextField(language, book.getLanguage(), Field.Store.YES));
		for (String genre : book.getGenres()) {
			doc.add(new TextField(BookIndexerServiceImpl.genre, genre, Field.Store.YES));
		}

//		TODO rewrite
		if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
			// New index, so we just add the document (no old document can be there):
			writer.addDocument(doc);
		} else {
			// Existing index (an old copy of this document may have been indexed) so
			// we use updateDocument instead to replace the old one matching the exact
			// path, if present:
			writer.updateDocument(new Term("path", book.getFileName()), doc);
		}
	}

}
