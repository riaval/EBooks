package ua.miratech.zhukov;

import junit.framework.Assert;
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
	public void userTest() {
//		long count = userRepository.count();
//
//		Assert.assertTrue(count >= 0);
	}

	@Test
	public void bookTest() {
//		long count = bookRepository.count();
//
//		Assert.assertTrue(count >= 0);
	}

}
