package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.miratech.zhukov.dto.output.ReadingBook;
import ua.miratech.zhukov.dto.enums.SharedType;
import ua.miratech.zhukov.dto.output.DownloadBook;
import ua.miratech.zhukov.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for readBook.jsp
 */
@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * Shows page with text of book
	 *
	 * @param bookId Book ID
	 * @param model Model for JSP
	 * @return Apache Tiles link
	 * @throws Exception
	 */
	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	public String printReadBookPage(@PathVariable String bookId, ModelMap model) throws Exception {
		ReadingBook readingBook = bookService.getReadingBookById(bookId);

		model.addAttribute("readingBook", readingBook);
		return "read-page-tiles";
	}

	/**
	 * Allows to download file from File Storage
	 *
	 * @param bookId Book ID
	 * @param response {@link javax.servlet.http.HttpServletRequest} Server response
	 * @return File with original name
	 */
	@RequestMapping(value = "/file/{bookId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_OCTET_STREAM_VALUE
	})
	@ResponseBody
	public FileSystemResource downloadFile(@PathVariable String bookId, HttpServletResponse response) {
		DownloadBook book = bookService.downloadBook(bookId);

		response.setHeader("content-Disposition", "attachment; filename=" + book.getFileName());

		return new FileSystemResource(book.getFilePath());
	}

	/**
	 * Allows to delete book
	 *
	 * @param bookId Book ID
	 * @param request {@link javax.servlet.http.HttpServletRequest} Server response
	 * @return Redirect before page
	 * @throws Exception
	 */
	@RequestMapping(value = "/book/delete/{bookId}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable String bookId, HttpServletRequest request) throws Exception {
		bookService.deleteBook(bookId);

		String previousPage = request.getHeader("Referer");
		return "redirect:" + previousPage;
	}

	/**
	 * Provides interface that allows to delete book
	 *
	 * @param bookId Book ID
	 * @throws IOException
	 */
	@RequestMapping(value = "/book/delete/{bookId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteBookAPI(@PathVariable String bookId) throws IOException {
		bookService.deleteBook(bookId);
	}

	/**
	 * Provides to change access settings for book (public-private)
	 *
	 * @param bookId Book ID
	 * @param type New access type (public или private)
	 */
	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.POST, params = {"type"})
	@ResponseStatus(value = HttpStatus.OK)
	public void setSharedType(@PathVariable String bookId, @RequestParam(value = "type") String type) {
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

	/**
	 * Allows to share book between users
	 *
	 * @param bookId Book ID
	 * @param email Grantee User email
	 */
	@RequestMapping(value = "/book/share/{bookId}", method = RequestMethod.POST, params = {"email"})
	@ResponseStatus(value = HttpStatus.CREATED)
	public void shareBook(@PathVariable String bookId, @RequestParam(value = "email") String email) {
		bookService.shareBook(bookId, email);
	}

	/**
	 * Allows to remove sharing from book
	 *
	 * @param bookId Book ID
	 * @param granteeId Granted User ID
	 */
	@RequestMapping(value = "/books/{bookId}/users/{granteeId}/delete", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void unShareBook(@PathVariable String bookId, @PathVariable String granteeId) {
		bookService.unShareBook(bookId, granteeId);
	}

}