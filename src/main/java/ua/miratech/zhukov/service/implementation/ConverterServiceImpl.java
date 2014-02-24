package ua.miratech.zhukov.service.implementation;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;
import ua.miratech.zhukov.dto.UploadedFile;
import ua.miratech.zhukov.dto.converter.CheckStatusRequest;
import ua.miratech.zhukov.dto.converter.UploadRequest;
import ua.miratech.zhukov.service.ConverterService;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

@Service
public class ConverterServiceImpl implements ConverterService {

	@Override
	public UploadedFile convertToFb2(UploadedFile uploadedFile) {
		byte[] newData = convertFile(uploadedFile.getBytes(), uploadedFile.getName());
		uploadedFile.setBytes(newData);
		String fileSimpleName = FilenameUtils.getName(uploadedFile.getName());
		uploadedFile.setName(fileSimpleName + ".fb2");

		return uploadedFile;
	}

	private byte[] convertFile(byte[] fileData, String fileName) {
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

//		String uploadUrl = "http://localhost:8080/Ebooks/books/bb/";
		String uploadUrl = "http://api.online-convert.com/queue-insert";
//		String uploadUrl = "http://api.online-convert.com/get-queue";
		UploadRequest uploadRequest = new UploadRequest();
		uploadRequest.getFile().setFileName("A_Tangeled_Web-Alan_Maley.mobi");
		uploadRequest.getFile().setFileData(Base64.encode(fileData));

		MultiValueMap<String, UploadRequest> uploadMap = new LinkedMultiValueMap<>();
		uploadMap.add("queue", uploadRequest);

		RestTemplate restTemplate = new RestTemplate();
		String uploadResponse = restTemplate.postForObject(uploadUrl, uploadMap, String.class);
		InputSource uploadSource = new InputSource(new StringReader(uploadResponse));
		String hash = null;
		try {
			hash = xpath.evaluate("/queue-answer/params/hash", uploadSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(hash);

//		Second part

		String downloadLink = null;
		while (downloadLink == null) {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String checkStatusUrl = "http://api.online-convert.com/queue-status";
			CheckStatusRequest checkStatusRequest = new CheckStatusRequest();
			checkStatusRequest.setHash(hash);
			MultiValueMap<String, CheckStatusRequest> checkMap = new LinkedMultiValueMap<>();
			checkMap.add("queue", checkStatusRequest);
			String checkStatusRespStr = restTemplate.postForObject(checkStatusUrl, checkMap, String.class);
			InputSource checkStatusSource = new InputSource(new StringReader(checkStatusRespStr));
			try {
				downloadLink = xpath.evaluate("/queue-answer/params/directDownload", checkStatusSource);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(downloadLink);
		}

//		Third part
		String fb2File = restTemplate.getForObject(downloadLink, String.class);

		return fb2File.getBytes();
	}

}
