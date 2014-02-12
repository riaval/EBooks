package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.miratech.zhukov.dto.UserOut;
import ua.miratech.zhukov.mapper.UserMapper;

import java.util.List;

@Controller
public class UserController {

	@Autowired(required = false)
	UserMapper userMapper;

	@RequestMapping(value = "/book/{bookId}/users", method = RequestMethod.GET)
	@ResponseBody
	public List<UserOut> getUserWithSharedBooks(@PathVariable Long bookId) {
		return userMapper.getUserWithSharedBooks(bookId);
	}

}
