package ua.miratech.zhukov.service;

import org.bson.types.ObjectId;
import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.dto.controller.CreatedUser;
import ua.miratech.zhukov.dto.controller.EditedUser;

import java.util.List;

public interface UserService {

	User createUser(CreatedUser createdUser);

	User getCurrentUser();

	ObjectId getCurrentUserObjectId();

	String getCurrentUserEmail();

	User editUser(EditedUser user, String userId);

	List<User> getUserWithSharedBooks(String bookId);
}
