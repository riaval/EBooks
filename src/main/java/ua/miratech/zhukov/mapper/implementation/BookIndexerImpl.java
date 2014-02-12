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
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;
import ua.miratech.zhukov.dto.IndexBook;
import ua.miratech.zhukov.mapper.BookIndexer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookIndexerImpl implements BookIndexer {

	private static final String INDEX_PATH = "D:/EBOOKS_STORAGE/INDEX_CATALOGUE";

	@Override
	public synchronized void doIndex(IndexBook book) throws IOException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		Directory dir = FSDirectory.open(new File(INDEX_PATH));

		System.out.println("in");
		IndexWriter writer = new IndexWriter(dir, iwc);
		createIndex(writer, book);
		writer.close();
		dir.close();
		System.out.println("out");
	}

	private void createIndex(IndexWriter writer, IndexBook book) throws IOException {
//		System.out.println("index creating");
		Document doc = new Document();

		Field pathField = new StringField("path", book.getFilePath(), Field.Store.YES);
		doc.add(pathField);

		doc.add(new TextField("contents", new String(book.getContent()), Field.Store.YES));

//		writer.addDocument(doc);
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

	public static List<Long> own(String queryString) throws IOException, ParseException {
//		String queryString = "\"It was Mr. Bean's birthday, and he wanted to enjoy it! What could he do?\"";
		String field = "contents";

		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
		IndexSearcher searcher = new IndexSearcher(reader);

		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		QueryParser parser = new QueryParser(Version.LUCENE_46, field, analyzer);
		Query query = parser.parse(queryString);

		TopDocs results = searcher.search(query, 10);
		ScoreDoc[] hits = results.scoreDocs;
//		hits[0].
		List<Long> ids = new ArrayList<>();
		for (int i = 0; i < hits.length; i++) {
			Document doc = searcher.doc(hits[i].doc);
			String path = doc.get("path");
			System.out.println(FilenameUtils.removeExtension(new File(path).getName()));
			Long id = Long.parseLong(FilenameUtils.removeExtension(new File(path).getName()));
			ids.add(id);
		}
		return ids;
//		System.out.println(results.totalHits);
	}

}
