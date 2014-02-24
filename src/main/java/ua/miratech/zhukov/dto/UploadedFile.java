package ua.miratech.zhukov.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties({"bytes"})
public class UploadedFile {

	private String name;
	private long size;
	private String type;
	private String deleteUrl;
	private String deleteType = "DELETE";

	private byte[] bytes;

	public UploadedFile() {}

	public UploadedFile(byte[] bytes, String name, long size, String type) {
		this.bytes = bytes;
		this.name = name;
		this.size = size;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UploadedFile that = (UploadedFile) o;

		return Arrays.equals(bytes, that.bytes);
	}

	@Override
	public int hashCode() {
		return bytes != null ? Arrays.hashCode(bytes) : 0;
	}

	public static class UploadedFiles {

		private List<UploadedFile> files;

		public UploadedFiles(List<UploadedFile> files) {
			this.files = files;
		}

		public List<UploadedFile> getFiles() {
			return files;
		}

		public void setFiles(List<UploadedFile> files) {
			this.files = files;
		}

	}
}