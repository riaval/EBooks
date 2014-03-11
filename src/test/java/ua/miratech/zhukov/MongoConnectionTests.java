package ua.miratech.zhukov;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.miratech.zhukov.repository.mongodb.BookRepository;
import ua.miratech.zhukov.repository.mongodb.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mongo-config.xml"})
public class MongoConnectionTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookRepository bookRepository;

	@Test
	public void userTest() {
		long count = userRepository.count();

		Assert.assertTrue(count >= 0);
	}

	@Test
	public void bookTest() {
		long count = bookRepository.count();

		Assert.assertTrue(count >= 0);
	}

}
