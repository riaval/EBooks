package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.service.BookService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String printIndexPage(ModelMap model) {
		int firstPageNumber = 1;
		return printIndexPage(model, firstPageNumber);
	}

	@RequestMapping(method = RequestMethod.GET, params = {"page"})
	public String printIndexPage(ModelMap model, @RequestParam(value = "page") Integer pageNumber) {
		Page<Book> books = bookService.getLastBooks(pageNumber - 1); // cause pageNumber min val = 1

		model.addAttribute("books", books.getContent());
		model.addAttribute("totalPages", books.getTotalPages());
		model.addAttribute("pageNumber", books.getNumber() + 1); // cause might return 0
		return "index-tiles";
	}

}