package com.bezkoder.spring.login.payload.response;


import com.bezkoder.spring.login.models.EStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class UserInfoResponse {
	private String username;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Date dob;
	private String email;
	private List<String> roles;

	public UserInfoResponse(String username, String firstName, String lastName, String phoneNumber, Date dob, String email, List<String> roles) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.email = email;
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName(){
		return firstName;
	}

	public void SetFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public Date getDob(){
		return dob;
	}

	public void setDob(Date dob){
		this.dob = dob;
	}

	public List<String> getRoles() {
		return roles;
	}
}
