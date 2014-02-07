package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.miratech.zhukov.dao.BookDAO;
import ua.miratech.zhukov.dto.Book;
import ua.miratech.zhukov.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/mybooks")
public class MyBooksController {

	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String printMyBooksPage(ModelMap model) {
		List<Book> books = bookService.getMyBooks();
		model.addAttribute("books", books);
		return "mybooks-tiles";
	}

}
