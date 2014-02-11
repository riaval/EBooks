package ua.miratech.zhukov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ua.miratech.zhukov.mapper.UserMapper;
import ua.miratech.zhukov.dto.UserIn;
import ua.miratech.zhukov.dto.UserInsert;

import java.util.Calendar;

@Service
public class UserService {

	private static final String ROLE_USER = "ROLE_USER";
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Autowired(required = false)
	UserMapper userMapper;

	public Long createUser(UserIn userIn){
		UserInsert userInsert = new UserInsert(
				  userIn
				, Calendar.getInstance().getTime()
				, ROLE_USER
		);
		String dbPass = BCrypt.hashpw(userInsert.getPassword(), BCrypt.gensalt());
		userInsert.setPassword(dbPass);

		userMapper.createNewUser(userInsert);

		return userInsert.getId();
	}

//	public UserOut getUserByEmail(String email) {
//		return userMapper.getUserByEmail(email);
//	}

}
