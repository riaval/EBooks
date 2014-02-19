package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.miratech.zhukov.dto.ReadingBook;
import ua.miratech.zhukov.dto.SharedType;
import ua.miratech.zhukov.service.BookService;
import ua.miratech.zhukov.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class BookController {

	@Autowired
	FileService fileService;

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	public String printReadBookPage(@PathVariable Long bookId, ModelMap model) throws Exception {
		ReadingBook readingBook = bookService.getReadingBookById(bookId);

		model.addAttribute("readingBook", readingBook);
		return "read-page-tiles";
	}

	@RequestMapping(value = "/file/{bookId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_OCTET_STREAM_VALUE
	})
	@ResponseBody
	public FileSystemResource downloadFile(@PathVariable Long bookId, HttpServletResponse response) {
		System.out.println(bookId);
		return fileService.uploadFile(bookId, response);
	}

	@RequestMapping(value = "/book/delete/{bookId}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable Long bookId, HttpServletRequest request) {
		bookService.deleteBook(bookId);

		String previousPage = request.getHeader("Referer");
		return "redirect:" + previousPage;
	}

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.POST, params = {"type"})
	@ResponseStatus(value = HttpStatus.OK)
	public void setType(@PathVariable Long bookId, @RequestParam(value = "type") String type) {
		SharedType sharedType = null;
		switch (type) {
			case "public":
				sharedType = SharedType.PUBLIC;
				break;
			case "private":
				sharedType = SharedType.PRIVATE;
				break;
		}

		bookService.setSharedType(bookId, sharedType);
	}

	@RequestMapping(value = "/book/share/{bookId}", method = RequestMethod.POST, params = {"email"})
	@ResponseStatus(value = HttpStatus.CREATED)
	public void shareBook(@PathVariable Long bookId, @RequestParam(value = "email") String email) {
		bookService.shareBook(bookId, email);
	}

	@RequestMapping(value = "/books/{bookId}/users/{userId}/delete", method = RequestMethod.POST)
	public ResponseEntity unShareBook(@PathVariable Long bookId, @PathVariable Long userId) {
		bookService.unShareBook(bookId, userId);

		return new ResponseEntity(HttpStatus.OK);
	}

}
