package ua.miratech.zhukov.dao;

import org.apache.ibatis.annotations.*;
import ua.miratech.zhukov.domain.Role;
import ua.miratech.zhukov.domain.User;

import java.util.List;

public interface UserDAO {

	public List<User> getUsers();

//	final String SELECT = "SELECT\n" +
//			"  *\n" +
//			"FROM\n" +
//			"  USERS u1\n" +
//			"    INNER JOIN\n" +
//			"  ROLES r1\n" +
//			"    ON u1.ROLE = r1.ID";
//
//	@Select(SELECT)
//	@Results(value = {
//			@Result(property = "id", column = "ID_1"),
//			@Result(property = "email", column = "EMAIL"),
//			@Result(property = "fullName", column = "FULLNAME")
//	})
//	@Results({
//			@Result(property = "id", column = "id"),
//			@Result(property = "person", column = "personId",
//					javaType = PersonVO.class,
//					one = @One(select = "com.examples.dao.PersonDAO.doSelectPerson")
//			),
//			@Result(property = "address", column = "addressId",
//					javaType = AddressVO.class,
//					one = @One(select = "com.examples.dao.AddressDAO.doSelectAddress")),
//			@Result(property = "sectors", column = "id",
//					javaType = List.class,
//					many = @Many(select = "com.examples.dao.SectorDAO.doSelectSectorsByCandidate")
//			)
//	})


//	@Select("SELECT * FROM USERS WHERE id = #{id}")
//	public User getUserById(int id);
//
//	@Insert("INSERT INTO USERS (EMAIL, FULLNAME, ROLE) VALUES ('#{email}', '#{fullName}', '#{roleID}')")
//	public void addUser();

}