package ua.miratech.zhukov.service;

import org.springframework.web.multipart.MultipartFile;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.dto.output.DownloadBook;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {

	List<UploadedFile> uploadFile(Map<String, MultipartFile> filesMap) throws IOException;

	boolean saveFile(byte[] bytes, Book book) throws IOException;

	void deleteFile(String storedIndex);

	DownloadBook getDownloadBook(Book book);

	void uploadZipFile(UploadedFile uploadedFile, User user) throws IOException;
}
