package ua.miratech.zhukov;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.mapper.BookMapper;
import ua.miratech.zhukov.mapper.BookMapperMock;
import ua.miratech.zhukov.service.*;
import ua.miratech.zhukov.service.implementation.BookServiceImpl;
import ua.miratech.zhukov.util.component.EbookStorage;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/testContext.xml"})
public class BookServiceImpTests {

	@Mock
	private BookMapper bookMapper = new BookMapperMock();

	@Autowired
	@Mock
	private EbookStorage ebookStorage;

	@Mock
	private FileService fileService = new FileServiceMock();

	@Mock
	private SecurityService securityService = new SecurityServiceMock();

	@InjectMocks
	private BookService bookService = new BookServiceImpl();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addBookTest() throws IOException {
//		UploadedFile uploadedFile = new UploadedFile(
//				text.getBytes(),
//				"file_name.fb2",
//				100500,
//				"text"
//		);
//		String userEmail = SecurityServiceMock.CURRENT_USER;
//		Long index = bookService.addBook(uploadedFile, userEmail);
//		System.out.println(index);
	}

	private final static String text = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
			"<FictionBook xmlns:l=\"http://www.w3.org/1999/xlink\" xmlns=\"http://www.gribuser.ru/xml/fictionbook/2.0\">\n" +
			"  <description>\n" +
			"    <title-info>\n" +
			"      <genre>epic</genre>\n" +
			"      <genre>fantasy</genre>\n" +
			"      <author>\n" +
			"        <first-name>George</first-name>\n" +
			"        <middle-name>R. R.</middle-name>\n" +
			"        <last-name>Martin</last-name>\n" +
			"      </author>\n" +
			"      <book-title>A Feast for Crows</book-title>\n" +
			"      <annotation>\n" +
			"        <p>Long-awaited doesn't begin to describe this fourth installment in bestseller Martin's staggeringly epic Song of Ice and Fire.</p>\n" +
			"      </annotation>\n" +
			"      <date>2005</date>\n" +
			"      <coverpage>\n" +
			"        <image l:href=\"#cover.png\" />\n" +
			"      </coverpage>\n" +
			"      <lang>en</lang>\n" +
			"      <src-lang>en</src-lang>\n" +
			"      <sequence number=\"4\" name=\"A Song of Ice and Fire\" />\n" +
			"    </title-info>\n" +
			"    <document-info>\n" +
			"      <author>\n" +
			"        <nickname>Roland</nickname>\n" +
			"        <email>ronaton@gmail.com</email>\n" +
			"      </author>\n" +
			"      <program-used>FB Tools</program-used>\n" +
			"      <date value=\"2005-11-16\">2005-11-16</date>\n" +
			"      <id>892C1FA2-BFD3-4394-9DE0-1663D20EB12C</id>\n" +
			"      <version>1.0</version>\n" +
			"    </document-info>\n" +
			"    <publish-info>\n" +
			"      <book-name>A Feast for Crows</book-name>\n" +
			"      <publisher>Spectra</publisher>\n" +
			"      <year>2005</year>\n" +
			"      <isbn>0553801503</isbn>\n" +
			"    </publish-info>\n" +
			"  </description>\n" +
			"  <body>\n" +
			"    <title>\n" +
			"      <p>George R. R. Martin</p>\n" +
			"      <p>A Feast for Crows</p>\n" +
			"    </title>\n" +
			"    <section>\n" +
			"      <title>\n" +
			"        <p>PROLOGUE</p>\n" +
			"      </title>\n" +
			"      <p id=\"AutBody_0prologue\">Dragons,” said Mollander. He snatched a withered apple off the ground and tossed it hand to hand.</p>\n" +
			"      <p>“Throw the apple,” urged Alleras the Sphinx. He slipped an arrow from his quiver and nocked it to his bowstring.</p>\n" +
			"    </section>   \n" +
			"  </body>\n" +
			"</FictionBook>";

}
