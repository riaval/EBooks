package ua.miratech.zhukov.mapper;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.mapper.UserInsert;
import ua.miratech.zhukov.dto.output.UserOut;

import java.util.List;

public interface UserMapper {

	public void createNewUser(UserInsert user);

	public UserOut getUserByEmail(
			  @Param("email") String email
	);

	public List<UserOut> getUserWithSharedBooks(
			  @Param("userEmail") String userEmail
			, @Param("bookId") Long bookId
	);

	public int updateName(
			  @Param("userEmail") String userEmail
			, @Param("firstName") String firstName
			, @Param("lastName") String lastName
	);

	public int updateFull(
			  @Param("userEmail") String userEmail
			, @Param("firstName") String firstName
			, @Param("lastName") String lastName
			, @Param("password") String password
	);

}