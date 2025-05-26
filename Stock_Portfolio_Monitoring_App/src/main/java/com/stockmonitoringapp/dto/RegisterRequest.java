package com.stockmonitoringapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
	@NotBlank(message="Username is required!")
	@Size(min=5,max=15,message="Username must be between 5 and 15 characters")
	@Pattern(regexp="^[A-Za-z0-9_]+$",message="Username must be Alphanumeric with the underscores only")
	    private String username;
	@NotBlank(message="Email is required!")
	@Email(message="Invalid Email format! Enter Valid Email")
	    private String email;
	@NotBlank(message="Password is required!")
	@Size(min=7,max=20,message="Password must be between 7 and 20 characters")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%.*?&]{7,20}$",
	message="Password should contain UpperCase and LowerCase letters,a number and special characters")
	    private String password;
	}
