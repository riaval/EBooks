package ua.miratech.zhukov.dto;

import java.util.Date;

public class Job {

	private Long id;
	private String userEmail;
	private String comment;
	private Date startTime;
	private Date endTime;

	public Job() {
	}

	public Job(String userEmail, String comment, Date startTime, Date endTime) {
		this.userEmail = userEmail;
		this.comment = comment;
		this.startTime = startTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
