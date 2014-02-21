package ua.miratech.zhukov.dto.converter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "queue")
public class CheckStatusRequest {

	private String apiKey = "90671026564474d9a2ab7fd530daf0e0";
	private String hash;

	public CheckStatusRequest() { }

	public CheckStatusRequest(String apiKey, String hash) {
		this.apiKey = apiKey;
		this.hash = hash;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
