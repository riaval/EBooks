package ua.miratech.zhukov.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ua.miratech.zhukov.dto.UserOut;
import ua.miratech.zhukov.dto.controller.EditedUser;
import ua.miratech.zhukov.mapper.UserMapper;
import ua.miratech.zhukov.dto.controller.CreatedUser;
import ua.miratech.zhukov.dto.UserInsert;
import ua.miratech.zhukov.service.UserService;

import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private static final String ROLE_USER = "ROLE_USER";
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Autowired(required = false)
	private UserMapper userMapper;

	@Autowired
	private SecurityServiceImpl securityService;

	@Override
	public Long createUser(CreatedUser createdUser) {
		UserInsert userInsert = new UserInsert(
				createdUser
				, Calendar.getInstance().getTime()
				, ROLE_USER
		);
		String dbPass = BCrypt.hashpw(userInsert.getPassword(), BCrypt.gensalt());
		userInsert.setPassword(dbPass);

		userMapper.createNewUser(userInsert);

		return userInsert.getId();
	}

	@Override
	public UserOut getCurrentUser() {
		String email = securityService.getUserEmail();
		return userMapper.getUserByEmail(email);
	}

	@Override
	public void editUser(EditedUser user, Long userId) {
		String userEmail = securityService.getUserEmail();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		int status = 0;

		if (user.getOldPassword().isEmpty()) {
			status = userMapper.updateName(userEmail, firstName, lastName);
		} else {
			String pass = user.getNewPassword();
			String passAgain = user.getNewPasswordAgain();
			if (pass.equals(passAgain)) {
				String dbPass = BCrypt.hashpw(pass, BCrypt.gensalt());
				status = userMapper.updateFull(userEmail, firstName, lastName, dbPass);
			}
		}

		if (status == 0) {
			throw new SecurityException(
					"User [email:" + userEmail + "] is not authorized to access this resource");
		}
	}

	@Override
	public List<UserOut> getUserWithSharedBooks(Long bookId) {
		String userEmail = securityService.getUserEmail();

		return userMapper.getUserWithSharedBooks(userEmail, bookId);
	}

}
