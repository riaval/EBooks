package ua.miratech.zhukov.service.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.dto.controller.CreatedUser;
import ua.miratech.zhukov.dto.controller.EditedUser;
import ua.miratech.zhukov.repository.BookRepository;
import ua.miratech.zhukov.repository.UserRepository;
import ua.miratech.zhukov.service.UserService;

import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private static final String ROLE_USER = "ROLE_USER";
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(CreatedUser createdUser) {
		String password = createdUser.getPassword();
		String passwordAgain = createdUser.getPasswordAgain();
		if (!password.equals(passwordAgain)) {
			throw new IllegalArgumentException("Passwords don't match");
		}

		String dbPass = BCrypt.hashpw(createdUser.getPassword(), BCrypt.gensalt());

		User user = new User(
				createdUser.getEmail(),
				dbPass,
				createdUser.getFirstName(),
				createdUser.getLastName(),
				Calendar.getInstance().getTime(),
				ROLE_USER
		);

		return userRepository.save(user);
	}

	@Override
	public User getCurrentUser() {
		String email = getUserEmail();

		return userRepository.findByEmail(email);
	}

	@Override
	public User editUser(EditedUser editedUser, Long userId) {
		String userEmail = getUserEmail();

		User user = userRepository.findByEmail(userEmail);

		user.setFirstName(editedUser.getFirstName());
		user.setLastName(editedUser.getLastName());

		if (!editedUser.getOldPassword().isEmpty()) {
			String password = editedUser.getNewPassword();
			String passwordAgain = editedUser.getNewPasswordAgain();
			if (!password.equals(passwordAgain)) {
				throw new IllegalArgumentException("Passwords don't match");
			}
			String dbPass = BCrypt.hashpw(password, BCrypt.gensalt());
			user.setPassword(dbPass);
		}

		return userRepository.save(user);
	}

	@Override
	public List<User> getUserWithSharedBooks(String bookId) {
		Book book = bookRepository.findOne(bookId);

		return book.getSharedFor();
	}

	private String getUserEmail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

}
