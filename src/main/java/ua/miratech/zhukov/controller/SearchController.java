package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.miratech.zhukov.dto.Book;
import ua.miratech.zhukov.lucene.FileIndexer;
import ua.miratech.zhukov.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String printSearchPage(ModelMap model) {
		return "search-tiles";
	}

	@RequestMapping(method = RequestMethod.GET, params = {"content"})
	public String simpleSearch(
			  ModelMap model
			, @RequestParam(value = "content") String content
	) {
		System.out.println("Simple search");

		List<Book> books = bookService.doSimpleSearch(content);
		for(Book book : books) {
			System.out.println(book.getTitle());
		}
		model.addAttribute("books", books);
		return "search-tiles";
	}

	@RequestMapping(method = RequestMethod.GET, params = {"content", "title", "author", "genre", "lang"})
	public String extendedSearch(
			  ModelMap model
			, @RequestParam(value = "content") String content
			, @RequestParam(value = "title") String title
			, @RequestParam(value = "author") String author
			, @RequestParam(value = "genre") String genre
			, @RequestParam(value = "lang") String lang

	) {
		System.out.println("Extended search");
		return "search-tiles";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getSearchContent(ModelMap model) {
		return "search-tiles";
	}

}
