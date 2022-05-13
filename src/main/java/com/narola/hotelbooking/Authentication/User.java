package com.narola.hotelbooking.Authentication;

public class User {
	private int id;
	private String email;
	private String password;
	private int userType;
	private int customerId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUsertype(int usertype) {
		this.userType = usertype;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerid) {
		this.customerId = customerid;
	}
	
	
}
