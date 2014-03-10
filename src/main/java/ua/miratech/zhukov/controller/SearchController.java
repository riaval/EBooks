package ua.miratech.zhukov.controller;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.dto.CheckedWord;
import ua.miratech.zhukov.dto.controller.SearchedBook;
import ua.miratech.zhukov.service.BookService;
import ua.miratech.zhukov.service.SpellCheckerService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	BookService bookService;

	@Autowired
	SpellCheckerService spellCheckerService;

	@RequestMapping(method = RequestMethod.GET)
	public String printSearchPage() {
		return "search-tiles";
	}

	@RequestMapping(method = RequestMethod.GET, params = {"content"})
	public String simpleSearch(
			  ModelMap model
			, @RequestParam(value = "content") String content
	) throws IOException, ParseException {
		List<Book> books = bookService.doSimpleSearch(content);
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

		model.addAttribute("books", books);
		model.addAttribute("checkedContent", checkedContent);
		model.addAttribute("checkedLink", checkedLink);
		return "search-tiles";
	}

	@RequestMapping(method = RequestMethod.GET, params = {"content", "title", "author", "language", "genre"})
	public String extendedSearch(
			  ModelMap model
			, @ModelAttribute SearchedBook searchedBook
	) throws IOException, ParseException {
		List<Book> books = bookService.doExtendedSearch(searchedBook);
		model.addAttribute("books", books);

		return "search-tiles";
	}

}
