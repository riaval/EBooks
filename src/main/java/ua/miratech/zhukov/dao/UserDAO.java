package ua.miratech.zhukov.dao;

import ua.miratech.zhukov.domain.Role;
import ua.miratech.zhukov.domain.User;

import java.util.List;

public interface UserDAO {

	public List<User> findAll();

}
