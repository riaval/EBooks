package ua.miratech.zhukov.service;

import org.springframework.web.multipart.MultipartFile;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.dto.output.Book;
import ua.miratech.zhukov.dto.output.DownloadBook;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileServiceMock implements FileService {

	@Override
	public List<UploadedFile> uploadFile(Map<String, MultipartFile> filesMap) throws IOException {
		return null;
	}

	@Override
	public boolean saveFile(byte[] bytes, Book book) throws IOException {
		return false;
	}

	@Override
	public void deleteFile(Long storedIndex) {

	}

	@Override
	public DownloadBook getDownloadBook(Book book) {
		return null;
	}

	@Override
	public void uploadZipFile(UploadedFile uploadedFile, String userEmail) throws IOException {

	}

}
