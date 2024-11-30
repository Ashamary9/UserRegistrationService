package com.user.register.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users_entity")
public class UserEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userid;
	
	//@Column(nullable=false, length=50)
	private String userName;
	
	//@Column(nullable=false, length=120, unique=true)
	private String email;
	
	//@Column(nullable=false, unique=true)	
	private String encryptedPassword;
	
	//@Column(nullable=false,length=20)
	private long phoneNo;
	
	//@Column(nullable=false, length=150)
	private String Address;

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEntity(long userid, String userName, String email, String encryptedPassword, long phoneNo,
			String address) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
		this.phoneNo = phoneNo;
		Address = address;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	
	
	
	
}
