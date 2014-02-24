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
	public List<UserOut> getUserWithSharedBooks(@Param("userEmail") String userEmail, @Param("bookId") Long bookId) {
		return null;
	}

	@Override
	public int updateName(@Param("userEmail") String userEmail, @Param("firstName") String firstName, @Param("lastName") String lastName) {
		return 0;
	}

	@Override
	public int updateFull(@Param("userEmail") String userEmail, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("password") String password) {
		return 0;
	}
}
