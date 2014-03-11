package ua.miratech.zhukov;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.dto.controller.EditedUser;
import ua.miratech.zhukov.dto.enums.SharedType;
import ua.miratech.zhukov.repository.mongodb.BookRepository;
import ua.miratech.zhukov.repository.mongodb.UserRepository;
import ua.miratech.zhukov.service.UserService;
import ua.miratech.zhukov.service.implementation.UserServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
		"file:src/main/webapp/WEB-INF/applicationContext.xml",
		"file:src/main/webapp/WEB-INF/spring-security.xml",
		"file:src/main/webapp/WEB-INF/mongo-config.xml"})
public class UserServiceImpTests {

	@Mock
	private BookRepository bookRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUserWithSharedBooksTest() {
		when(bookRepository.findOne(bookId)).thenReturn(book);

		List<User> retUsers = userService.getUserWithSharedBooks(bookId);
		List<User> realUsers = new ArrayList<>(Arrays.asList(new User[]{sharedUser1, sharedUser2}));
		assertEquals(retUsers, realUsers);
	}

	@Test(expected=NullPointerException.class)
	public void editUserTest() {
		when(userRepository.findByEmail("test@example.com")).thenReturn(user);
		when(userService.getCurrentUserEmail()).thenReturn("test@example.com");

		userService.editUser(editedUserNotMatched, userId);
	}

	String bookId = "1b14f8b4e63346ba94452cb0d21dc080";
	String userId = "9a98f8b4e63346ba94452cb0d21dc080";

	User user = new User(
			"test@example.com",
			"password",
			"firstName",
			"lastName",
			Calendar.getInstance().getTime(),
			"ROLE_USER"
	);

	User sharedUser1 = new User(
			"email1",
			"password1",
			"firstName1",
			"lastName1",
			Calendar.getInstance().getTime(),
			"ROLE_USER1"
	);

	User sharedUser2 = new User(
			"email2",
			"password2",
			"firstName2",
			"lastName2",
			Calendar.getInstance().getTime(),
			"ROLE_USER2"
	);

	Book book = new Book(
			"author",
			"title",
			"annotation",
			"isbn",
			"language",
			new ArrayList<>(Arrays.asList(new String[]{"genre1", "genre2"})),
			"fileName.fb2",
			"fb2",
			(long) 100500,
			"md5",
			user,
			SharedType.PRIVATE.toString(),
			"storedIndex",
			Calendar.getInstance().getTime(),
			new ArrayList<>(Arrays.asList(new User[]{sharedUser1, sharedUser2}))
	);

	EditedUser editedUserNotMatched = new EditedUser(
			"firstName",
			"lastName",
			"oldPassword",
			"newPassword",
			"newPasswordAgain"
	);

}
