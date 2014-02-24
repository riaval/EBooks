package ua.miratech.zhukov;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.miratech.zhukov.service.implementation.ConverterServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:../../../../../main/webapp/WEB-INF/test-context/controller.xml")
public class UserServiceTests {

//	@Mock
//	private UserMapper userMapper;
//
//	@InjectMocks
//	private UserService userService;

    @Test
    public void simple() throws Exception {
		System.out.println("---------------------------------------------");
		Path path = Paths.get("D:/A_Tangeled_Web-Alan_Maley.mobi");
		byte[] data = Files.readAllBytes(path);
//		System.out.println(new String(Base64.encode(data)));
		Base64.encode(data);
		ConverterServiceImpl converterService = new ConverterServiceImpl();
//		converterService.convertFile(Base64.encode(data));

//		CreatedUser userInParam = new CreatedUser();
    }

//	private CreatedUser userInParam;
//
//	{
//		userInParam.setEmail("ls2294@gmail.com");
//		userInParam.setFirstName("firstName");
//		userInParam.setLastName("lastName");
//		userInParam.setPassword("");
//	}
}
