package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.miratech.zhukov.dto.UserIn;
import ua.miratech.zhukov.service.UserService;

import javax.validation.Valid;

@Controller
public class SecurityController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String printLogInPage(ModelMap model) {
		return "sign-in-tiles";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String printSignUpPage(ModelMap model) {
		return "sign-up-tiles";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String doSignUp(@Valid UserIn userIn, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/signup";
		}

		boolean passMatch = userIn.getPassword().equals(userIn.getPasswordAgain());
		if (!passMatch) {
			return "redirect:/signup?error=not_match";
		}

		userService.createUser(userIn);
		return "redirect:/login";
	}

}
