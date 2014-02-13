package ua.miratech.zhukov.mapper.implementation;

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
import org.springframework.stereotype.Component;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.dto.SearchBook;
import ua.miratech.zhukov.mapper.BookIndexer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookIndexerImpl implements BookIndexer {

	private static final String INDEX_PATH = "D:/EBOOKS_STORAGE/INDEX_CATALOGUES";
	private static final String CONTENT = "content";
	private static final String AUTHOR = "author";
	private static final String TITLE = "title";
	private static final String LANGUAGE = "language";
	private static final String GENRE = "genre";

	@Override
	public synchronized void doIndex(IndexBook book) throws IOException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		Directory dir = FSDirectory.open(new File(INDEX_PATH));

		IndexWriter writer = new IndexWriter(dir, iwc);
		createIndex(writer, book);
		writer.close();
		dir.close();
	}

	private void createIndex(IndexWriter writer, IndexBook book) throws IOException {
		Document doc = new Document();

		Field pathField = new StringField("path", book.getFilePath(), Field.Store.YES);
		doc.add(pathField);

		doc.add(new TextField(CONTENT, new String(book.getContent()), Field.Store.YES));
		doc.add(new TextField(TITLE, book.getTitle(), Field.Store.YES));
		doc.add(new TextField(AUTHOR, book.getAuthor(), Field.Store.YES));
		doc.add(new TextField(LANGUAGE, book.getLanguage(), Field.Store.YES));
		for (String genre : book.getGenres()) {
			doc.add(new TextField(GENRE, genre, Field.Store.YES));
		}

//		TODO rewrite
		if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
			// New index, so we just add the document (no old document can be there):
			writer.addDocument(doc);
		} else {
			// Existing index (an old copy of this document may have been indexed) so
			// we use updateDocument instead to replace the old one matching the exact
			// path, if present:
			writer.updateDocument(new Term("path", book.getFilePath()), doc);
		}
	}

	@Override
	public List<Long> doSimpleSearch(String content) throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		if (content.isEmpty()) {
			return null;
		}

		Query contentQuery = new QueryParser(Version.LUCENE_46, CONTENT, analyzer).parse(content);

		return doSearch(contentQuery);
	}

	@Override
	public List<Long> doExtendedSearch(SearchBook book) throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		BooleanQuery booleanQuery = new BooleanQuery();

		if (!book.getContent().isEmpty()) {
			Query contentQuery = new QueryParser(Version.LUCENE_46, CONTENT, analyzer).parse(book.getContent());
			booleanQuery.add(contentQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getTitle().isEmpty()) {
			Query titleQuery = new QueryParser(Version.LUCENE_46, TITLE, analyzer).parse(book.getTitle());
			booleanQuery.add(titleQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getAuthor().isEmpty()) {
			Query authorQuery = new QueryParser(Version.LUCENE_46, AUTHOR, analyzer).parse(book.getAuthor());
			booleanQuery.add(authorQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getLanguage().isEmpty()) {
			Query languageQuery = new QueryParser(Version.LUCENE_46, LANGUAGE, analyzer).parse(book.getLanguage());
			booleanQuery.add(languageQuery, BooleanClause.Occur.MUST);
		}

		if (!book.getGenre().isEmpty()) {
			Query genreQuery = new QueryParser(Version.LUCENE_46, GENRE, analyzer).parse(book.getGenre());
			booleanQuery.add(genreQuery, BooleanClause.Occur.MUST);
		}

		return doSearch(booleanQuery);
	}

	private List<Long> doSearch (Query query) throws IOException {
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);

		TopDocs results = searcher.search(query, 10);
		ScoreDoc[] hits = results.scoreDocs;
		List<Long> ids = new ArrayList<>();
		for (ScoreDoc hit : hits) {
			Document doc = searcher.doc(hit.doc);
			String path = doc.get("path");
			Long id = Long.parseLong(FilenameUtils.removeExtension(new File(path).getName()));
			ids.add(id);
		}
		reader.close();

		return ids;
	}

}
