
package com.usersystem.UserSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserDetailsDto {

	private int userId;
	@NotNull(message = "this is not empty")
	private String fname;
	@NotEmpty(message = "this is not empty")
	private String lname;
	private int contactNumber;
	private boolean isenabled;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return "nagpal";
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	public boolean isIsenabled() {
		return isenabled;
	}
	public void setIsenabled(boolean isenabled) {
		this.isenabled = isenabled;
	}
	
	
	

}
