package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.miratech.zhukov.dto.SharedType;
import ua.miratech.zhukov.dto.UserOut;
import ua.miratech.zhukov.service.BookService;
import ua.miratech.zhukov.service.FileService;
import ua.miratech.zhukov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BookController {

	@Autowired
	FileService fileService;

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	public String printReadBookPage(@PathVariable Long bookId, ModelMap model) {
		bookService.getBookContent(bookId, model);
		return "read-page-tiles";
	}

	@RequestMapping(value = "/file/{bookId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_OCTET_STREAM_VALUE
	})
	@ResponseBody
	public FileSystemResource downloadFile(@PathVariable Long bookId, HttpServletResponse response) {
		return fileService.downloadFile(bookId, response);
	}

	@RequestMapping(value = "/book/delete/{bookId}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable Long bookId, HttpServletRequest request) {
		bookService.deleteBook(bookId);

		String previousPage = request.getHeader("Referer");
		return "redirect:" + previousPage;
	}

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.POST, params = {"type"})
	public ResponseEntity  setType(@PathVariable Long bookId, @RequestParam(value = "type") String type) {
		SharedType sharedType = null;
		switch (type) {
			case "public" :
				sharedType = SharedType.PUBLIC;
				break;
			case "private" :
				sharedType = SharedType.PRIVATE;
				break;
		}
		try {
			bookService.setSharedType(bookId, sharedType);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/book/share/{bookId}", method = RequestMethod.POST, params = {"email"})
	public ResponseEntity  shareBook(@PathVariable Long bookId, @RequestParam(value = "email") String email) {
		try {
			bookService.shareBook(bookId, email);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/books/{bookId}/users/{userId}/delete", method = RequestMethod.POST)
	public ResponseEntity  unShareBook(@PathVariable Long bookId, @PathVariable Long userId) {
		bookService.unShareBook(bookId, userId);

		return new ResponseEntity(HttpStatus.OK);
	}

}
