package ua.miratech.zhukov.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import ua.miratech.zhukov.dao.BookDAO;
import ua.miratech.zhukov.dto.Book;
import ua.miratech.zhukov.dto.SharedType;
import ua.miratech.zhukov.util.FictionBookParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class BookService {

	@Autowired(required = false)
	BookDAO bookDAO;

	@Autowired
	FileService fileService;

	public void getBookContent(Long bookId, ModelMap model) {
		Book book = bookDAO.getBookById(bookId);
		File file = new File(book.getPath() + book.getId() + "." + book.getExtension());
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			byte[] bytes = IOUtils.toByteArray(fis);
			FictionBookParser fbp = new FictionBookParser(bytes);
			model.addAttribute("selections", fbp.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		model.addAttribute("bookMeta", book);
	}

	public List<Book> getMyBooks() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		return bookDAO.getMyBooks(userEmail, "DESC");
	}

	public List<Book> getLastBooks() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();

		return bookDAO.getLastBooks(1, 10, userEmail);
	}

	public void deleteBook(Long id) {
		bookDAO.deleteBook(id);
		fileService.deleteFile(id);
	}

	public void setSharedType(Long bookId, SharedType sharedType) {
		bookDAO.setSharedType(bookId, sharedType.name());
	}

}
