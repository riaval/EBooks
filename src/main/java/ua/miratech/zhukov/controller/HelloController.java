package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.miratech.zhukov.dao.UserDAO;
import ua.miratech.zhukov.domain.User;

import java.util.List;

@Controller
@RequestMapping("/users")
public class HelloController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String selectUser(ModelMap model) {
		List<User> users = userDAO.getUsers();

		System.out.println(users.get(0).getRole());

		model.addAttribute("users", users);
		return "hello";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createUser(ModelMap model) {

		return "redirect:/users";
	}

}