package ua.miratech.zhukov.util.component.factory;

import org.springframework.stereotype.Component;
import ua.miratech.zhukov.util.FictionBookParser;

@Component
public class FictionBookParserFactory {

	public FictionBookParser createFictionBookParser(byte[] bytes) {
		return new FictionBookParser(bytes);
	}

}
