package ua.miratech.zhukov.service;

import ua.miratech.zhukov.dto.output.UserOut;
import ua.miratech.zhukov.dto.controller.CreatedUser;
import ua.miratech.zhukov.dto.controller.EditedUser;

import java.util.List;

public interface UserService {

	Long createUser(CreatedUser createdUser);

	UserOut getCurrentUser();

	void editUser(EditedUser user, Long userId);

	List<UserOut> getUserWithSharedBooks(Long bookId);
}
