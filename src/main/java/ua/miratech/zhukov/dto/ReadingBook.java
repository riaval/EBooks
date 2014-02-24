package ua.miratech.zhukov.dto;

import org.w3c.dom.NodeList;
import ua.miratech.zhukov.dto.output.Book;

public class ReadingBook extends Book {

	private NodeList content;

	public ReadingBook() {
	}

	public ReadingBook(Book book, NodeList content) {
		super(
				book.getAuthor(),
				book.getTitle(),
				book.getPublicationDate(),
				book.getFileName(),
				book.getSize(),
				book.getSha1(),
				book.getOwner(),
				book.getLanguage(),
				book.getExtension(),
				book.getAnnotation(),
				book.getIsbn(),
				book.getSharedType(),
				book.getGenres()
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
