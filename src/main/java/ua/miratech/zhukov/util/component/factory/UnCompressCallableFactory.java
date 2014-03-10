package ua.miratech.zhukov.util.component.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.service.FileService;
import ua.miratech.zhukov.service.UserService;
import ua.miratech.zhukov.util.thread.UnCompressCallable;

@Component
public class UnCompressCallableFactory {

	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;

	public UnCompressCallable createUnCompressCallable(UploadedFile uploadedFile) {
		return new UnCompressCallable(
				fileService,
				uploadedFile,
				userService.getCurrentUser()
		);
	}

}
