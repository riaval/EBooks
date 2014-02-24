package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.miratech.zhukov.dto.controller.EditedBook;
import ua.miratech.zhukov.dto.output.Book;
import ua.miratech.zhukov.service.BookService;

import javax.validation.Valid;

@Controller
public class EditBookController {

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/book/{bookId}/edit", method = RequestMethod.GET)
	public String printEditBookPage(@PathVariable Long bookId, ModelMap model) throws Exception {
		Book book = bookService.getBookForEditing(bookId);

		model.addAttribute("book", book);
		return "edit-page-tiles";
	}

	@RequestMapping(value = "/book/{bookId}/edit", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void editBook(@PathVariable Long bookId, @Valid EditedBook book) throws Exception {
		book.setId(bookId);
		bookService.updateBook(book);
	}

}
