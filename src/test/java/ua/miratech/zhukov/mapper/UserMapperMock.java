package ua.miratech.zhukov.mapper;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.mapper.UserInsert;
import ua.miratech.zhukov.dto.output.UserOut;

import java.util.List;


public class UserMapperMock implements UserMapper {


	@Override
	public void createNewUser(UserInsert user) {

	}

	@Override
	public UserOut getUserByEmail(String email) {
		return null;
	}

	@Override
	public List<UserOut> getUserWithSharedBooks(String userEmail, Long bookId) {
		return null;
	}

	@Override
	public int updateName(String userEmail, String firstName, String lastName) {
		return 0;
	}

	@Override
	public int updateFull(String userEmail, String firstName, String lastName, String password) {
		return 0;
	}
}
