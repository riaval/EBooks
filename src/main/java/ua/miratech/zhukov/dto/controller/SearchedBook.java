package ua.miratech.zhukov.dto.controller;

public class SearchedBook {

	String content;
	String title;
	String author;
	String genre;
	String language;

	public SearchedBook() {}

	public SearchedBook(String content, String title, String author, String language, String genre) {
		this.content = content;
		this.title = title;
		this.author = author;
		this.language = language;
		this.genre = genre;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
