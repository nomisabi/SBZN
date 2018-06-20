package com.example.demo.dto;


public class UserPasswordDTO {
	private String currentPassword;
	private String newPassword1;
	private String newPassword2;
	
	public UserPasswordDTO(){
		
	}

	public UserPasswordDTO(String currentPassword, String newPassword1, String newPassword2) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword1 = newPassword1;
		this.newPassword2 = newPassword2;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

}
