package ua.miratech.zhukov.service;

import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.dto.controller.CreatedUser;
import ua.miratech.zhukov.dto.controller.EditedUser;
import ua.miratech.zhukov.dto.output.UserOut;

import java.util.List;

public interface UserService {

	User createUser(CreatedUser createdUser);

	User getCurrentUser();

	User editUser(EditedUser user, Long userId);

	List<UserOut> getUserWithSharedBooks(Long bookId);
}
