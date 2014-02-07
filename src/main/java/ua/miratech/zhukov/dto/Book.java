package ua.miratech.zhukov.dto;

import java.util.Date;
import java.util.List;

public class Book {

	private Long id;
	private String author;
	private String title;
	private Date publicationDate;
	private String fileName;
	private Long size;
	private String path;
	private String sha1;
	private String owner;
	private String language;
	private String extension;
	private String annotation;
	private String isbn;
	private String sharedType;
	private List<String> genres;

	public Book() {}

	public Book(String author, String title, Date publicationDate, String fileName, Long size, String path,
				String sha1, String owner, String language, String extension, String annotation, String isbn,
				String sharedType, List<String> genres) {
		this.author = author;
		this.title = title;
		this.publicationDate = publicationDate;
		this.fileName = fileName;
		this.size = size;
		this.path = path;
		this.sha1 = sha1;
		this.owner = owner;
		this.language = language;
		this.extension = extension;
		this.annotation = annotation;
		this.isbn = isbn;
		this.sharedType = sharedType;
		this.genres = genres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSha1() {
		return sha1;
	}

	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
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

	public String getSharedType() {
		return sharedType;
	}

	public void setSharedType(String sharedType) {
		this.sharedType = sharedType;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
}
