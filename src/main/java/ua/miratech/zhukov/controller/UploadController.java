package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ua.miratech.zhukov.service.FileService;
import ua.miratech.zhukov.dto.UploadedFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	FileService fileService;

	@RequestMapping(method = RequestMethod.GET)
	public String printUploadPage(ModelMap model) {
		return "upload-tiles";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public UploadedFile.UploadedFiles uploadFile(MultipartHttpServletRequest request) throws Exception {
		Map<String, MultipartFile> filesMap = request.getFileMap();
		List<UploadedFile> uploadedFiles = fileService.uploadFile(filesMap);
		return new UploadedFile.UploadedFiles(uploadedFiles);
	}

}
