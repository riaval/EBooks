package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/mybooks")
public class MyBooksController {

	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String printMyBooksPage(ModelMap model) {
		int firstPageNumber = 1;
		return printMyBooksPage(model, firstPageNumber);
	}

	@RequestMapping(method = RequestMethod.GET, params = {"page"})
	public String printMyBooksPage(ModelMap model, @RequestParam(value = "page") Integer pageNumber) {
		Page<Book> books = bookService.getMyBooks(pageNumber - 1);

		model.addAttribute("books", books.getContent());
		model.addAttribute("totalPages", books.getTotalPages());
		model.addAttribute("pageNumber", books.getNumber() + 1);
		return "mybooks-tiles";
	}

}
