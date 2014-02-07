package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.miratech.zhukov.service.BookService;
import ua.miratech.zhukov.service.FileService;

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

	@RequestMapping(value = "/file/{bookId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
	@ResponseBody
	public FileSystemResource downloadFile(@PathVariable("bookId") Long bookId, HttpServletResponse response) {
		return fileService.downloadFile(bookId, response);
	}

}
