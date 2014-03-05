package ua.miratech.zhukov.dto.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class EditedUser {

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	private String oldPassword;

	private String newPassword;

	private String newPasswordAgain;

	public EditedUser() { }

	public EditedUser(String firstName, String lastName, String oldPassword, String newPassword,
					  String newPasswordAgain) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPasswordAgain = newPasswordAgain;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}

	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}
}
