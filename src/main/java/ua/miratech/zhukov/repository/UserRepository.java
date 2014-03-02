package ua.miratech.zhukov.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.miratech.zhukov.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("{ 'email' : ?0 }")
	public User findByEmail(String email);

}
