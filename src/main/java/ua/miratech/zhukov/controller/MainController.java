package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.miratech.zhukov.dto.output.Book;
import ua.miratech.zhukov.service.BookService;
import ua.miratech.zhukov.service.implementation.BookServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String printIndexPage(ModelMap model) {
		List<Book> books = bookService.getLastBooks();

		model.addAttribute("books", books);
		return "index-tiles";
	}

}