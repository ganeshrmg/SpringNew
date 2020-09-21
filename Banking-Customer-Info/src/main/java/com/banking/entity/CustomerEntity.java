package com.banking.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class CustomerEntity implements Serializable {
	
	private static final long serialVersionUID = 4518290119722159053L;
	
		
	@Id
	@Column(name = "USERID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
	@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
	private Long userid;
	
	@Column(name="NAME")
    private String name;

	@Column
	private String username;
	
	@Column
	private String address;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column
	private String state;
	
	@Column
	private String country;
	
	@Column
	private String pan;
	
	@Column
	private String contact;
	
	@Column
	private Date dob;
	
	@Column
	private String account;
	
	@Column
	private String password;
	
	@Column
    private Integer score;
	
	@Column
    private String duestatus;
	
	@Column
    private String dueapproval;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getDuestatus() {
		return duestatus;
	}

	public void setDuestatus(String duestatus) {
		this.duestatus = duestatus;
	}

	public String getDueapproval() {
		return dueapproval;
	}

	public void setDueapproval(String dueapproval) {
		this.dueapproval = dueapproval;
	}	

}
