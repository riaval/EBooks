package ua.miratech.zhukov.util.thread;

import ua.miratech.zhukov.service.FileService;
import ua.miratech.zhukov.dto.UploadedFile;

import java.util.concurrent.Callable;

public class UnCompressCallable implements Callable<Boolean> {

	private FileService fileService;
	private UploadedFile uploadedFile;
	private String userEmail;

	public UnCompressCallable(FileService fileService, UploadedFile uploadedFile, String userEmail) {
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
