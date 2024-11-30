package com.user.register.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.user.register.data.UserEntity;
import com.user.register.shared.UserDto;

public interface UserService {

	
	
	public List<UserDto> getAllUsers();
	public UserDetails loadUserByUsername(String email);
	public UserEntity createUser(UserEntity user);
	
	
}
