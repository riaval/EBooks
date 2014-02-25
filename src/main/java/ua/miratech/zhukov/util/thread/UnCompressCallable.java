package ua.miratech.zhukov.util.thread;

import org.apache.log4j.Logger;
import ua.miratech.zhukov.service.FileService;
import ua.miratech.zhukov.service.implementation.FileServiceImpl;
import ua.miratech.zhukov.dto.UploadedFile;

import java.util.concurrent.Callable;

public class UnCompressCallable implements Callable<Boolean> {

	private static final Logger logger = Logger.getLogger(UnCompressCallable.class);

	private FileService fileService;
	private UploadedFile uploadedFile;
	private String userEmail;

	public UnCompressCallable(FileServiceImpl fileService, UploadedFile uploadedFile, String userEmail) {
		this.fileService = fileService;
		this.uploadedFile = uploadedFile;
		this.userEmail = userEmail;
	}

	@Override
	public Boolean call() throws Exception {
		logger.info("-> Thread with zip extracting [file_name:" + uploadedFile.getName() + "] started");
		fileService.uploadZipFile(uploadedFile, userEmail);
		logger.info("-> Thread with zip extracting [file_name:" + uploadedFile.getName() + "] finished");
		return true;
	}

}
