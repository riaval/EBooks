package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.dto.output.UserOut;
import ua.miratech.zhukov.dto.controller.EditedUser;
import ua.miratech.zhukov.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/book/{bookId}/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUserWithSharedBooks(@PathVariable String bookId) {
		return userService.getUserWithSharedBooks(bookId);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String printProfilePage(ModelMap model) {
		User user = userService.getCurrentUser();

		model.addAttribute("user", user);
		return "profile-page-tiles";
	}

	@RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void editUser(@Valid EditedUser user, @PathVariable Long userId) {
		userService.editUser(user, userId);
	}

}
