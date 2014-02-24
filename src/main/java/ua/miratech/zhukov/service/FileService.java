package ua.miratech.zhukov.service;

import org.springframework.web.multipart.MultipartFile;
import ua.miratech.zhukov.dto.UploadedFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {

	List<UploadedFile> uploadFile(Map<String, MultipartFile> filesMap) throws IOException;

}
