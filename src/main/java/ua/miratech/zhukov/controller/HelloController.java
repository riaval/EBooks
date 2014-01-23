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
@RequestMapping("/books/add/")
public class HelloController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");

		List<User> users = userDAO.findAll();
		model.addAttribute("users", users);

		return "new_book";
	}

}