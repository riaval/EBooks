package ua.miratech.zhukov;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.miratech.zhukov.service.ConverterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/test.xml")
public class ConverterTests {

	@Test
	public void simple() throws Exception {
//		System.out.println("---------------------------------------------");
//		ConverterService converterService = new ConverterService();
//		converterService.convertFile();
	}

}
