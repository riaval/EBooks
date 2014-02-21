package ua.miratech.zhukov.dto.converter;

import javax.xml.bind.annotation.XmlElement;

public class Status {
	private String code;

	private String message;

	@XmlElement(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	@XmlElement(name = "message")
	public void setMessage(String message) {
		this.message = message;
	}
}