package ua.miratech.zhukov.dto.mapper;

public class ShareInParam {

	// Input
	private Long bookId;
	private String ownerEmail;
	private String granteeEmail;

	// Output
	private Integer resultStatus;

	public ShareInParam(Long bookId, String ownerEmail, String granteeEmail) {
		this.bookId = bookId;
		this.ownerEmail = ownerEmail;
		this.granteeEmail = granteeEmail;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getGranteeEmail() {
		return granteeEmail;
	}

	public void setGranteeEmail(String granteeEmail) {
		this.granteeEmail = granteeEmail;
	}

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}
}
