package com.user.register.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.register.data.UserEntity;
import com.user.register.service.UserService;
import com.user.register.shared.LoginDto;
import com.user.register.shared.LoginResponse;
import com.user.register.shared.UserDto;
import com.user.register.utils.JwtTokenProvider;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class UserController {

	
	@Autowired
	private UserService userservice;
	
	@Autowired
    private JwtTokenProvider jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

	
    @PostMapping("/login")
    public LoginResponse createAuthenticationToken(@RequestBody LoginDto authenticationDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
       
    	try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getEncryptedPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userservice.loadUserByUsername(authenticationDTO.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new LoginResponse(jwt);
    	

    }

    
    
	    @GetMapping("/getAllUsers")
	    public ResponseEntity<List<UserDto>> getAllUsers() {
	        List<UserDto> users = userservice.getAllUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	
	
	    @PostMapping("/register")
	    public ResponseEntity<String> createUser(@RequestBody UserEntity user) {
	        try {
	        	userservice.createUser(user);
	            return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error registering customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	
	
}
