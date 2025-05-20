package com.stockmonitoringapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmonitoringapp.dto.AuthRequest;
import com.stockmonitoringapp.dto.AuthResponse;
import com.stockmonitoringapp.dto.RegisterRequest;
import com.stockmonitoringapp.model.User;
import com.stockmonitoringapp.repository.UserRepository;
import com.stockmonitoringapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	public UserService userservice;
	@Autowired
	public UserRepository userrepo;
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest regreq){
		AuthResponse response=userservice.register(regreq);
		return ResponseEntity.ok(response);
	}
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody AuthRequest authreq){
		AuthResponse response=userservice.login(authreq);
		return ResponseEntity.ok(response);
	}
	 @GetMapping
	    public List<String> getAllUsernames() {
	        List<User> users = userrepo.findAll();
	        List<String> usernames = new ArrayList<>();

	        for (User user : users) {
	            usernames.add(user.getUsername());
	        }

	        return usernames;
	    }

	@GetMapping("/user/{username}")
	public ResponseEntity<String> getUserByName(@PathVariable String username){
		Optional<User> user=userrepo.findByUsername(username);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get().toString());
		}else{
		return ResponseEntity.ok("User not found");
		}		   
	}
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody RegisterRequest updateduser){
		Optional<User> optuser=userrepo.findById(id);
		if(optuser.isEmpty()) {
			return ResponseEntity.ok("User not found");
		}
		User existingUser=optuser.get();
		existingUser.setUsername(updateduser.getUsername());
		existingUser.setEmail(updateduser.getEmail());
		existingUser.setPassword(userservice.encodePassword(updateduser.getPassword()));
		existingUser.setRole(updateduser.getRole());
		User saved=userrepo.save(existingUser);
		return ResponseEntity.ok(saved);
		
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		if(!userrepo.existsById(id)) {
			return ResponseEntity.ok("User not found");
		}
		userrepo.deleteById(id);
		return ResponseEntity.ok("User deleted successfully:)");
		
	}

}
