package ua.miratech.zhukov.dto.converter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "queue-answer")
public class CheckStatusResponse {


	private Status status;
//	@XmlElementRef(type = Params.class)
//	private Params params;

	@XmlElement(name = "status")
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

//
//	public static class Params {
//		@XmlElement
//		private String downloadCounter;
//		@XmlElement
//		private String dateProcessed;
//		@XmlElement
//		private String directDownload;
//		@XmlElement
//		private String source_checksum;
//		@XmlElement
//		private String checksum;
//		@XmlElement
//		private String target_size;
//		@XmlElement
//		private String convert_to;
//		@XmlElement
//		private String mime_type;
//		@XmlElement
//		private String hash;
//
//		public String getDownloadCounter() {
//			return downloadCounter;
//		}
//
//		public void setDownloadCounter(String downloadCounter) {
//			this.downloadCounter = downloadCounter;
//		}
//
//		public String getDateProcessed() {
//			return dateProcessed;
//		}
//
//		public void setDateProcessed(String dateProcessed) {
//			this.dateProcessed = dateProcessed;
//		}
//
//		public String getDirectDownload() {
//			return directDownload;
//		}
//
//		public void setDirectDownload(String directDownload) {
//			this.directDownload = directDownload;
//		}
//
//		public String getSource_checksum() {
//			return source_checksum;
//		}
//
//		public void setSource_checksum(String source_checksum) {
//			this.source_checksum = source_checksum;
//		}
//
//		public String getChecksum() {
//			return checksum;
//		}
//
//		public void setChecksum(String checksum) {
//			this.checksum = checksum;
//		}
//
//		public String getTarget_size() {
//			return target_size;
//		}
//
//		public void setTarget_size(String target_size) {
//			this.target_size = target_size;
//		}
//
//		public String getConvert_to() {
//			return convert_to;
//		}
//
//		public void setConvert_to(String convert_to) {
//			this.convert_to = convert_to;
//		}
//
//		public String getMime_type() {
//			return mime_type;
//		}
//
//		public void setMime_type(String mime_type) {
//			this.mime_type = mime_type;
//		}
//
//		public String getHash() {
//			return hash;
//		}
//
//		public void setHash(String hash) {
//			this.hash = hash;
//		}
//	}

}

