package ua.miratech.zhukov.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String printLogInPage(ModelMap model) {
		return "sign-in-tiles";
	}

//	@RequestMapping(value = "/signup", method = RequestMethod.GET)
//	public String signUp(ModelMap model) {
//		return "sign-up-tiles";
//	}

}
