package com.banking.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

public class Customer {
	
	@NotNull(message="Name must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String name;
	
	
	@NotNull(message="Username must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String username;	
	
	@NotNull(message="Address must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String address;
	
	@NotNull(message="Email must be Required")
	@Email(message = "Email should be valid")	
	private String email;
	
	@NotNull(message="State must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String state;
	
	@NotNull(message="Country must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String country;
	
	@NotNull(message="PAN must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String pan;
	
	@NotNull(message="contact must be Required")
	@Size(min=10,message="Minimum digits Required")
	private String contact;
	
	@NotNull(message="Dob must be Required")
	private Date dob;
	
	@NotNull(message="Account must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String account;
	
	@NotNull(message="Password must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String password;
	
	
	private Integer score;	
    private String duestatus;
	private String dueapproval;
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer custScore) {
		this.score = custScore;
	}
	public String getDuestatus() {
		return duestatus;
	}
	public void setDuestatus(String custDuestatus) {
		this.duestatus = custDuestatus;
	}
	public String getDueapproval() {
		return dueapproval;
	}
	public void setDueapproval(String custDueapproval) {
		this.dueapproval = custDueapproval;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String custAddress) {
		this.address = custAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String custEmail) {
		this.email = custEmail;
	}
	public String getState() {
		return state;
	}
	public void setState(String custState) {
		this.state = custState;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String custCountry) {
		this.country = custCountry;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String custPan) {
		this.pan = custPan;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String custContact) {
		this.contact = custContact;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date custDob) {
		this.dob = custDob;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String custAccount) {
		this.account = custAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String custPassword) {
		this.password = custPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String customerName) {
		this.name = customerName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userFullname) {
		this.username = userFullname;
	}
	
}
