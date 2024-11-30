package com.user.register.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.user.register.filter.JwtRequestFilter;
import com.user.register.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {


    @Autowired
    private JwtRequestFilter requestFilter;
	
	
	
	
	    @Bean
	    public AuthenticationManager authenticationManager(
	                                 AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
	
	 
	  
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  
	  
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.csrf().disable()
	               .authorizeHttpRequests()
	                .requestMatchers("/api/login", "/api/register","/api/**").permitAll()
	                .and()
	                .authorizeHttpRequests().requestMatchers("/api/**")
	                .authenticated().and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
	                .build();
	        
	        
	    }
	  
	  
	  
	  
	  
	  /*  @Bean
	    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    	
	    	  http.csrf((csrf)->csrf.disable());
	                 http.authorizeHttpRequests()
	                 .requestMatchers("/login", "/signup","/api/**").permitAll()
	                 .and()
	                 .authorizeHttpRequests().requestMatchers("/api/**").permitAll()
	                 .anyRequest()
	                 .authenticated().and()
	                 .sessionManagement()
	                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                 .and()
	                 .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
	               return  http.build();
	    	
	               http.csrf((csrf) -> csrf.disable());
             http.authorizeHttpRequests((authorize) ->
                      //authorize.anyRequest().authenticated()
                      authorize.requestMatchers("/register","/login", "/api/**").permitAll() //HttpMethod.POST
                              .requestMatchers("/api/**").permitAll()
                              .anyRequest().authenticated()
                              .and()
                              .sessionManagement()
                              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                              .and()
                              .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class));

              

      return http.build();*/


}
	  
	
	
	

