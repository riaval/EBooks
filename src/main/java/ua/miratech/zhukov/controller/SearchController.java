package ua.miratech.zhukov.controller;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.dto.CheckedWord;
import ua.miratech.zhukov.dto.controller.SearchedBook;
import ua.miratech.zhukov.service.BookService;
import ua.miratech.zhukov.service.SpellCheckerService;
import ua.miratech.zhukov.service.implementation.SpellCheckerServiceImpl;

import java.io.IOException;
import java.util.List;

@Controller
public class SearchController {

	@Autowired
	BookService bookService;

	@Autowired
	SpellCheckerService spellCheckerService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String printSearchPage() {
		return "search-tiles";
	}

	@RequestMapping(value = "/search/{page}", method = RequestMethod.GET, params = {"content"})
	public String simpleSearch(
			  ModelMap model,
			  @RequestParam(value = "content") String content,
			  @PathVariable("page") Integer pageNumber
	) throws IOException, ParseException {
		Page<Book> books = bookService.doSimpleSearch(content, pageNumber - 1);
		List<CheckedWord> words = spellCheckerService.check(content);

		StringBuilder checkedContent = new StringBuilder();
		StringBuilder checkedLink = new StringBuilder();

		if (!(words == null)) {
			for(CheckedWord each : words) {
				checkedContent.append(
						each.isRight() ? each.getWord() : "<strong>" + each.getWord() + "</strong>"
				).append(" ");
				checkedLink.append(each.getWord()).append("+");
			}

		}

		model.addAttribute("books", books.getContent());
		model.addAttribute("totalPages", books.getTotalPages());
		model.addAttribute("pageNumber", books.getNumber() + 1); // cause might return 0
		model.addAttribute("checkedContent", checkedContent);
		model.addAttribute("checkedLink", checkedLink);
		return "search-tiles";
	}

	@RequestMapping(
			value = "/search/{page}",
			method = RequestMethod.GET,
			params = {"content", "title", "author", "language", "genre"}
	)
	public String extendedSearch(
			  ModelMap model,
			  @ModelAttribute SearchedBook searchedBook,
			  @PathVariable("page") Integer pageNumber
	) throws IOException, ParseException {
		Page<Book> books = bookService.doExtendedSearch(searchedBook, pageNumber - 1);

		model.addAttribute("books", books.getContent());
		model.addAttribute("totalPages", books.getTotalPages());
		model.addAttribute("pageNumber", books.getNumber() + 1); // cause might return 0
		return "search-tiles";
	}

}
