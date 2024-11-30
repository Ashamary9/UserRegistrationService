package com.user.register.shared;

import java.io.Serializable;

public class UserDto implements Serializable{

	/**
	 * 
	 */
	 
	private static final long serialVersionUID = 5010695004367127937L;
	
	
	private String userName;
	private String email;
	private long phoneNo;
	private String encryptedPassword;
	private String Address;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	
	
	
	
}
