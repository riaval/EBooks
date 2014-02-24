package ua.miratech.zhukov.util.thread;

import ua.miratech.zhukov.service.implementation.FileServiceImpl;
import ua.miratech.zhukov.dto.UploadedFile;

import java.util.concurrent.Callable;

public class UnCompressCallable implements Callable<Boolean> {

	private FileServiceImpl fileService;
	private UploadedFile uploadedFile;
	private String userEmail;

	public UnCompressCallable(FileServiceImpl fileService, UploadedFile uploadedFile, String userEmail) {
		this.fileService = fileService;
		this.uploadedFile = uploadedFile;
		this.userEmail = userEmail;
	}

	@Override
	public Boolean call() throws Exception {
		fileService.uploadZipFile(uploadedFile, userEmail);

		System.out.println("UnCompressCallable exit");
		return true;
	}

}
