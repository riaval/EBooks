package ua.miratech.zhukov;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.miratech.zhukov.domain.Book;
import ua.miratech.zhukov.domain.User;
import ua.miratech.zhukov.repository.BookRepository;
import ua.miratech.zhukov.repository.UserRepository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mongo-config.xml"})
public class MongoConnectionTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookRepository bookRepository;

	@Test
	public void connectionTest() {
//		User user = new User("ls2294@gmai.com", "password", "fist", "last",
//				Calendar.getInstance().getTime(),
//				"ROLE_USER");
//
//		if (!mongoTemplate.collectionExists(User.class)) {
//			mongoTemplate.createCollection(User.class);
//		}
//		user.setId(UUID.randomUUID());
//		mongoTemplate.insert(user);

		User user = userRepository.findByEmail("ls2294@gmail.com");
		System.out.println(user.getFirstName());
//		Long size = userRepository.count();
//		System.out.println(size);
	}

	@Test
	public void bookTest() {
		System.out.println(bookRepository.findAll());
//		List<Book> books = bookRepository.findAll();
	}

}
