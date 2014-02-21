package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.miratech.zhukov.dto.UserOut;
import ua.miratech.zhukov.mapper.UserMapper;
import ua.miratech.zhukov.service.UserService;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired(required = false)
	private UserMapper userMapper;

	@RequestMapping(value = "/book/{bookId}/users", method = RequestMethod.GET)
	@ResponseBody
	public List<UserOut> getUserWithSharedBooks(@PathVariable Long bookId) {
		return userMapper.getUserWithSharedBooks(bookId);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String printProfilePage() {
		UserOut user = userService.getCurrentUser();

		return "profile-page-tiles";
	}

}
