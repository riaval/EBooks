package ua.miratech.zhukov.dto;

import java.util.Date;
import java.util.List;

public class IndexBook {

	private String author;
	private String title;
	private Date publicationDate;
	private Long size;
	private String fileName;
	private String language;
	private String annotation;
	private String isbn;
	private List<String> genres;
	private byte[] content;

	public IndexBook(String author, String title, Date publicationDate, Long size, String fileName, String language,
					 String annotation, String isbn, List<String> genres, byte[] content) {
		this.author = author;
		this.title = title;
		this.publicationDate = publicationDate;
		this.size = size;
		this.fileName = fileName;
		this.language = language;
		this.annotation = annotation;
		this.isbn = isbn;
		this.genres = genres;
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
