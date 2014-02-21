package ua.miratech.zhukov.dto.converter;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "queue-answer")
public class UploadResponse {

	@XmlAnyElement
	Status status = new Status();
//	Params params = new Params();

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

//	public Params getParams() {
//		return params;
//	}
//
//	public void setParams(Params params) {
//		this.params = params;
//	}

	public static class Status {
		private Integer code;
		private String message;

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return "Status{" +
					"code=" + code +
					", message='" + message + '\'' +
					'}';
		}
	}

	public static class Params {
		private String downloadUrl;
		private String hash;

		public String getDownloadUrl() {
			return downloadUrl;
		}

		public void setDownloadUrl(String downloadUrl) {
			this.downloadUrl = downloadUrl;
		}

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		@Override
		public String toString() {
			return "Params{" +
					"downloadUrl='" + downloadUrl + '\'' +
					", hash='" + hash + '\'' +
					'}';
		}
	}

//	@Override
//	public String toString() {
//		return "UploadResponse{" +
//				"status=" + status +
//				", params=" + params +
//				'}';
//	}
}