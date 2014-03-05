package ua.miratech.zhukov.dto.output;

import org.w3c.dom.NodeList;
import ua.miratech.zhukov.domain.Book;

public class ReadingBook extends Book {

	private NodeList content;

	public ReadingBook() { }

	public ReadingBook(Book book, NodeList content) {
		super(
				book.getAuthor(),
				book.getTitle(),
				book.getAnnotation(),
				book.getIsbn(),
				book.getLanguage(),
				book.getGenres(),
				book.getFileName(),
				book.getExtension(),
				book.getFileSize(),
				book.getMd5(),
				book.getOwner(),
				book.getSharedType(),
				book.getStoredIndex(),
				book.getPublicationDate(),
				book.getSharedFor()
			);
		this.content = content;
	}

	public NodeList getContent() {
		return content;
	}

	public void setContent(NodeList content) {
		this.content = content;
	}
}
