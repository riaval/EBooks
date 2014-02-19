package ua.miratech.zhukov;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ua.miratech.zhukov.dto.UserIn;
import ua.miratech.zhukov.mapper.UserMapper;
import ua.miratech.zhukov.service.BookService;
import ua.miratech.zhukov.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTests {

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserService userService;

    @Test
    public void simple() throws Exception {
//		UserIn userIn = new UserIn();
    }

	private UserIn userIn;

	{
		userIn.setEmail("ls2294@gmail.com");
		userIn.setFirstName("firstName");
		userIn.setLastName("lastName");
		userIn.setPassword("");
	}
}
