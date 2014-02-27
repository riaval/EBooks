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
 * Прямая связь с readBook.jsp
 * Контроллер для управления книгой на странице чтения
 */
@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * Отображает страницу с книгой для чтения
	 *
	 * @param bookId ID книги
	 * @param model Модель для JSP страницы
	 * @return Apache Tiles ссылку на страницу чтения
	 * @throws Exception
	 */
	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	public String printReadBookPage(@PathVariable Long bookId, ModelMap model) throws Exception {
		ReadingBook readingBook = bookService.getReadingBookById(bookId);

		model.addAttribute("readingBook", readingBook);
		return "read-page-tiles";
	}

	/**
	 * Позволяет скачать файл из файлохранилища
	 *
	 * @param bookId ID книги
	 * @param response {@link javax.servlet.http.HttpServletRequest} Ответ сервера
	 * @return Файл из хранилища с оригинальным названием
	 */
	@RequestMapping(value = "/file/{bookId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_OCTET_STREAM_VALUE
	})
	@ResponseBody
	public FileSystemResource downloadFile(@PathVariable Long bookId, HttpServletResponse response) {
		DownloadBook book = bookService.downloadBook(bookId);

		response.setHeader("content-Disposition", "attachment; filename=" + book.getFileName());

		return new FileSystemResource(book.getFilePath());
	}

	/**
	 * Позволяет удалить книгу с сервиса
	 *
	 * @param bookId ID книги
	 * @param request {@link javax.servlet.http.HttpServletRequest} Ответ сервера
	 * @return Redirect на предыдущую страницу
	 * @throws Exception
	 */
	@RequestMapping(value = "/book/delete/{bookId}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable Long bookId, HttpServletRequest request) throws Exception {
		bookService.deleteBook(bookId);

		String previousPage = request.getHeader("Referer");
		return "redirect:" + previousPage;
	}

	/**
	 * Предоставляет интерфейс для удаления книги с сервиса
	 *
	 * @param bookId ID книги
	 * @throws IOException
	 */
	@RequestMapping(value = "/book/delete/{bookId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteBookAPI(@PathVariable Long bookId) throws IOException {
		bookService.deleteBook(bookId);
	}

	/**
	 * Позволяет изменить параметры доступа к книге, public-private
	 *
	 * @param bookId ID книги
	 * @param type Новый тип доступа (public или private)
	 */
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

	/**
	 * Позволяет расшарить права доступа к книги другому пользователю
	 *
	 * @param bookId ID книги
	 * @param email Email пользователя которому предоставляют доступ
	 */
	@RequestMapping(value = "/book/share/{bookId}", method = RequestMethod.POST, params = {"email"})
	@ResponseStatus(value = HttpStatus.CREATED)
	public void shareBook(@PathVariable Long bookId, @RequestParam(value = "email") String email) {
		bookService.shareBook(bookId, email);
	}

	/**
	 * Позволяет убрать предоставленные права доступа к книге
	 *
	 * @param bookId ID книги
	 * @param userId ID пользователя, которому был предоставлен доступ
	 */
	@RequestMapping(value = "/books/{bookId}/users/{userId}/delete", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void unShareBook(@PathVariable Long bookId, @PathVariable Long userId) {
		bookService.unShareBook(bookId, userId);
	}

}