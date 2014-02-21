package ua.miratech.zhukov.dto.converter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "queue")
public class UploadRequest {

	private String apiKey = "90671026564474d9a2ab7fd530daf0e0";
	private String targetType = "ebook";
	private String targetMethod = "convert-to-fb2";
	private Boolean testMode = true;

	private File file = new File();

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	public Boolean getTestMode() {
		return testMode;
	}

	public void setTestMode(Boolean testMode) {
		this.testMode = testMode;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public static class File {

		private String fileName;

		private String fileData;

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileData() {
			return fileData;
		}

		public void setFileData(String fileData) {
			this.fileData = fileData;
		}

	}
}