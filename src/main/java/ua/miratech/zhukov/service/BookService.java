package ua.miratech.zhukov.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import ua.miratech.zhukov.mapper.BookMapper;
import ua.miratech.zhukov.mapper.UserMapper;
import ua.miratech.zhukov.dto.Book;
import ua.miratech.zhukov.dto.SharedType;
import ua.miratech.zhukov.dto.UserOut;
import ua.miratech.zhukov.util.FictionBookParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class BookService {

	@Autowired(required = false)
	BookMapper bookMapper;

	@Autowired(required = false)
	UserMapper userMapper;

	@Autowired
	FileService fileService;

	public void getBookContent(Long bookId, ModelMap model) {
		Book book = bookMapper.getBookById(bookId);
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
		return bookMapper.getMyBooks(userEmail, "DESC");
	}

	public List<Book> getLastBooks() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();

		return bookMapper.getLastBooks(1, 10, userEmail);
	}

	public void deleteBook(Long id) {
		bookMapper.delete(id);
		fileService.deleteFile(id);
	}

	public void setSharedType(Long bookId, SharedType sharedType) {
		bookMapper.setSharedType(bookId, sharedType.name());
	}

	public void shareBook(Long bookId, String grantee) {
		UserOut user = userMapper.getUserByEmail(grantee);
		if (user == null) {
			throw new RuntimeException("User not found");
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String owner = auth.getName();

		bookMapper.share(bookId, owner, grantee);
	}

	public void unShareBook(Long bookId, Long userId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();

		bookMapper.unShareBook(bookId, userEmail, userId);
	}

}
