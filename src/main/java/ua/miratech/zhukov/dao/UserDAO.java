package ua.miratech.zhukov.dao;

import ua.miratech.zhukov.dto.UserInsert;
import ua.miratech.zhukov.dto.UserOut;

public interface UserDAO {

	public void createNewUser(UserInsert user);

	public UserOut getUserByEmail(String email);

}
