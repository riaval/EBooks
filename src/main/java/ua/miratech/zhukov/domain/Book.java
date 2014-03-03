package ua.miratech.zhukov.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "books")
public class Book {

	@Id
	private String id;
	private String author;
	private String title;
	private String annotation;
	private String isbn;
	private String language;
	private List<String> genres;
	private String fileName;
	private String extension;
	private Long fileSize;
	private String md5;
	@DBRef
	private User owner;
	private String sharedType;
	private String storedIndex;
	private Date publicationDate;
	@DBRef
	private List<User> sharedFor;

	public Book() { }

	public Book(String author, String title, String annotation, String isbn, String language, List<String> genres,
				String fileName, String extension, Long fileSize, String md5, User owner, String sharedType,
				String storedIndex, Date publicationDate, List<User> sharedFor) {
		this.author = author;
		this.title = title;
		this.annotation = annotation;
		this.isbn = isbn;
		this.language = language;
		this.genres = genres;
		this.fileName = fileName;
		this.extension = extension;
		this.fileSize = fileSize;
		this.md5 = md5;
		this.owner = owner;
		this.sharedType = sharedType;
		this.storedIndex = storedIndex;
		this.publicationDate = publicationDate;
		this.sharedFor = sharedFor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getSharedType() {
		return sharedType;
	}

	public void setSharedType(String sharedType) {
		this.sharedType = sharedType;
	}

	public String getStoredIndex() {
		return storedIndex;
	}

	public void setStoredIndex(String storedIndex) {
		this.storedIndex = storedIndex;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public List<User> getSharedFor() {
		return sharedFor;
	}

	public void setSharedFor(List<User> sharedFor) {
		this.sharedFor = sharedFor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (annotation != null ? !annotation.equals(book.annotation) : book.annotation != null) return false;
		if (author != null ? !author.equals(book.author) : book.author != null) return false;
		if (extension != null ? !extension.equals(book.extension) : book.extension != null) return false;
		if (fileName != null ? !fileName.equals(book.fileName) : book.fileName != null) return false;
		if (fileSize != null ? !fileSize.equals(book.fileSize) : book.fileSize != null) return false;
		if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
		if (id != null ? !id.equals(book.id) : book.id != null) return false;
		if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
		if (language != null ? !language.equals(book.language) : book.language != null) return false;
		if (md5 != null ? !md5.equals(book.md5) : book.md5 != null) return false;
		if (owner != null ? !owner.equals(book.owner) : book.owner != null) return false;
		if (publicationDate != null ? !publicationDate.equals(book.publicationDate) : book.publicationDate != null)
			return false;
		if (sharedFor != null ? !sharedFor.equals(book.sharedFor) : book.sharedFor != null) return false;
		if (sharedType != null ? !sharedType.equals(book.sharedType) : book.sharedType != null) return false;
		if (storedIndex != null ? !storedIndex.equals(book.storedIndex) : book.storedIndex != null) return false;
		if (title != null ? !title.equals(book.title) : book.title != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (author != null ? author.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (annotation != null ? annotation.hashCode() : 0);
		result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
		result = 31 * result + (language != null ? language.hashCode() : 0);
		result = 31 * result + (genres != null ? genres.hashCode() : 0);
		result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
		result = 31 * result + (extension != null ? extension.hashCode() : 0);
		result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
		result = 31 * result + (md5 != null ? md5.hashCode() : 0);
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		result = 31 * result + (sharedType != null ? sharedType.hashCode() : 0);
		result = 31 * result + (storedIndex != null ? storedIndex.hashCode() : 0);
		result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
		result = 31 * result + (sharedFor != null ? sharedFor.hashCode() : 0);
		return result;
	}
}
