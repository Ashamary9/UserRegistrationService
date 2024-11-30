package com.user.register.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.register.data.UserEntity;
import com.user.register.repository.UserEntityRepository;
import com.user.register.service.UserService;
import com.user.register.shared.UserDto;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	private UserEntityRepository userRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserDto> getAllUsers() {
		
		List<UserEntity> userEntity = userRepo.findAll();
	     
        return userEntity.stream()
        		.map(userentity->modelmapper.map(userentity, UserDto.class))
        		.collect(Collectors.toList());
        		
	}

	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		 //Write Logic to get the user from the DB
        UserEntity user = userRepo.findFirstByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found",null);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
    
	}

	@Override
	public UserEntity createUser(UserEntity user) {
      user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));

		
		return userRepo.save(user);
	}

	

	
}
