package ua.miratech.zhukov.mapper;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.UserInsert;
import ua.miratech.zhukov.dto.UserOut;

import java.util.List;

public interface UserMapper {

	public void createNewUser(UserInsert user);

	public UserOut getUserByEmail(@Param("email") String email);

	public List<UserOut> getUserWithSharedBooks(@Param("bookId") Long bookId);

}