package com.stockmonitoringapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stockmonitoringapp.dto.AuthRequest;
import com.stockmonitoringapp.dto.AuthResponse;
import com.stockmonitoringapp.dto.RegisterRequest;
import com.stockmonitoringapp.model.User;
import com.stockmonitoringapp.Configuration.AppConfig;
import com.stockmonitoringapp.Configuration.SwaggerConfig;


import com.stockmonitoringapp.repository.UserRepository;
@Service
public class UserService implements UserServiceInterface{
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired 
	private BCryptPasswordEncoder passwordencoder;
	
	@Override
	public AuthResponse register(RegisterRequest request) {
		if(userrepo.existsByUsername(request.getUsername())) {
			throw new RuntimeException("oops!Username is already exists.Try again");
		}
		if(userrepo.existsByEmail(request.getEmail())) {
			throw new RuntimeException("oops!Email is already exists.Try again");
		}
		User user=new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordencoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		User savedUser=userrepo.save(user);
		  return new AuthResponse(savedUser.getUsername(),"Registration successful",savedUser.getRole());
	}
	
	@Override
	public AuthResponse login(AuthRequest request){
		User user=userrepo.findByUsername(request.getUsername()).orElse(null);
		  if(user==null) {
			  throw new RuntimeException("User not found");
		  }
		if(!passwordencoder.matches(request.getPassword(),user.getPassword())){
			throw new RuntimeException("Invalid credentials");
		}
		return new AuthResponse(user.getUsername(),"Login Successful",user.getRole());
		
	}

    public String encodePassword(String rawPassword) {
        return passwordencoder.encode(rawPassword);
    }
    }
