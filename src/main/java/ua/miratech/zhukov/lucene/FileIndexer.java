package ua.miratech.zhukov.lucene;

import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileIndexer {

	private static final String INDEX_PATH = "D:/EBOOKS_STORAGE/INDEX_CATALOGUE";

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

	public static void main() throws Exception {
		String index = INDEX_PATH;
		String field = "contents";
		String queries = null;
		int repeat = 0;
		boolean raw = false;
		String queryString = "Mr. Bean's";
		int hitsPerPage = 10;

		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		BufferedReader in = null;
		if (queries != null) {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(queries), "UTF-8"));
		} else {
			in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		}
		QueryParser parser = new QueryParser(Version.LUCENE_46, field, analyzer);
		while (true) {
			if (queries == null && queryString == null) {                        // prompt the user
				System.out.println("Enter query: ");
			}

			String line = queryString != null ? queryString : in.readLine();

			if (line == null || line.length() == -1) {
				break;
			}

			line = line.trim();
			if (line.length() == 0) {
				break;
			}

			Query query = parser.parse(line);
			System.out.println("Searching for: " + query.toString(field));

			if (repeat > 0) {                           // repeat & time as benchmark
				Date start = new Date();
				for (int i = 0; i < repeat; i++) {
					searcher.search(query, null, 100);
				}
				Date end = new Date();
				System.out.println("Time: "+(end.getTime()-start.getTime())+"ms");
			}

			doPagingSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null);

			if (queryString != null) {
				break;
			}
		}
		reader.close();
	}

	/**
	 * This demonstrates a typical paging search scenario, where the search engine presents
	 * pages of size n to the user. The user can then go to the next page if interested in
	 * the next hits.
	 *
	 * When the query is executed for the first time, then only enough results are collected
	 * to fill 5 result pages. If the user wants to page beyond this limit, then the query
	 * is executed another time and all hits are collected.
	 *
	 */
	public static void doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query,
									  int hitsPerPage, boolean raw, boolean interactive) throws IOException {

		// Collect enough docs to show 5 pages
		TopDocs results = searcher.search(query, 5 * hitsPerPage);
		ScoreDoc[] hits = results.scoreDocs;

		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits + " total matching documents");

		int start = 0;
		int end = Math.min(numTotalHits, hitsPerPage);

		while (true) {
			if (end > hits.length) {
				System.out.println("Only results 1 - " + hits.length +" of " + numTotalHits + " total matching documents collected.");
				System.out.println("Collect more (y/n) ?");
				String line = in.readLine();
				if (line.length() == 0 || line.charAt(0) == 'n') {
					break;
				}

				hits = searcher.search(query, numTotalHits).scoreDocs;
			}

			end = Math.min(hits.length, start + hitsPerPage);

			for (int i = start; i < end; i++) {
				if (raw) {                              // output raw format
					System.out.println("doc="+hits[i].doc+" score="+hits[i].score);
					continue;
				}

				Document doc = searcher.doc(hits[i].doc);
				String path = doc.get("path");
				if (path != null) {
					System.out.println((i+1) + ". " + path);
					String title = doc.get("title");
					if (title != null) {
						System.out.println("   Title: " + doc.get("title"));
					}
				} else {
					System.out.println((i+1) + ". " + "No path for this document");
				}

			}

			if (!interactive || end == 0) {
				break;
			}

			if (numTotalHits >= end) {
				boolean quit = false;
				while (true) {
					System.out.print("Press ");
					if (start - hitsPerPage >= 0) {
						System.out.print("(p)revious page, ");
					}
					if (start + hitsPerPage < numTotalHits) {
						System.out.print("(n)ext page, ");
					}
					System.out.println("(q)uit or enter number to jump to a page.");

					String line = in.readLine();
					if (line.length() == 0 || line.charAt(0)=='q') {
						quit = true;
						break;
					}
					if (line.charAt(0) == 'p') {
						start = Math.max(0, start - hitsPerPage);
						break;
					} else if (line.charAt(0) == 'n') {
						if (start + hitsPerPage < numTotalHits) {
							start+=hitsPerPage;
						}
						break;
					} else {
						int page = Integer.parseInt(line);
						if ((page - 1) * hitsPerPage < numTotalHits) {
							start = (page - 1) * hitsPerPage;
							break;
						} else {
							System.out.println("No such page");
						}
					}
				}
				if (quit) break;
				end = Math.min(numTotalHits, start + hitsPerPage);
			}
		}
	}

//	INDEX ------------------------------

	public synchronized static void indexFile(String filePath) {
		File file = new File(filePath);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		try {
			Directory dir = FSDirectory.open(new File(INDEX_PATH));
			IndexWriter writer = new IndexWriter(dir, iwc);
			indexDocs(writer, file);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// do not try to index files that cannot be read
	public static void indexDocs(IndexWriter writer, File file)
			throws IOException {
		// do not try to index files that cannot be read
		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				// an IO error could occur
				if (files != null) {
					for (String each : files) {
						indexDocs(writer, new File(file, each));
					}
				}
			} else {

				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException fnfe) {
					// at least on windows, some temporary files raise this exception with an "access denied" message
					// checking if the file can be read doesn't help
					return;
				}

				try {

					// make a new, empty document
					Document doc = new Document();

					// Add the path of the file as a field named "path".  Use a
					// field that is indexed (i.e. searchable), but don't tokenize
					// the field into separate words and don't index term frequency
					// or positional information:
					Field pathField = new StringField("path", file.getPath(), Field.Store.YES);
					doc.add(pathField);

					// Add the last modified date of the file a field named "modified".
					// Use a LongField that is indexed (i.e. efficiently filterable with
					// NumericRangeFilter).  This indexes to milli-second resolution, which
					// is often too fine.  You could instead create a number based on
					// year/month/day/hour/minutes/seconds, down the resolution you require.
					// For example the long value 2011021714 would mean
					// February 17, 2011, 2-3 PM.
					doc.add(new LongField("modified", file.lastModified(), Field.Store.NO));

					// Add the contents of the file to a field named "contents".  Specify a Reader,
					// so that the text of the file is tokenized and indexed, but not stored.
					// Note that FileReader expects the file to be in UTF-8 encoding.
					// If that's not the case searching for special characters will fail.
					doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(fis, "UTF-8"))));

					if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
						// New index, so we just add the document (no old document can be there):
						System.out.println("adding " + file);
						writer.addDocument(doc);
					} else {
						// Existing index (an old copy of this document may have been indexed) so
						// we use updateDocument instead to replace the old one matching the exact
						// path, if present:
						System.out.println("updating " + file);
						writer.updateDocument(new Term("path", file.getPath()), doc);
					}

				} finally {
					fis.close();
				}
			}
		}
	}

}