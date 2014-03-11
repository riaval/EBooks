package ua.miratech.zhukov.repository.mongodb;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.miratech.zhukov.domain.User;

/**
 * Spring Data repository for {@link ua.miratech.zhukov.domain.User} User
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

	/**
	 * Returns users from DMS by Email
	 *
	 * @param email User email
	 * @return {@link ua.miratech.zhukov.domain.User} User
	 */
	@Query("{ 'email' : ?0 }")
	User findByEmail(String email);

}
