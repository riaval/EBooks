package ua.miratech.zhukov.mapper;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.UserInsert;
import ua.miratech.zhukov.dto.UserOut;

import java.util.List;


public class UserMapperMock implements UserMapper {

	@Override
	public void createNewUser(UserInsert user) {

	}

	@Override
	public UserOut getUserByEmail(@Param("email") String email) {
		return null;
	}

	@Override
	public List<UserOut> getUserWithSharedBooks(@Param("bookId") Long bookId) {
		return null;
	}

}
