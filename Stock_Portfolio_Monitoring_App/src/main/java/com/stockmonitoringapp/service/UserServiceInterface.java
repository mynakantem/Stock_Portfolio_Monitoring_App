package com.stockmonitoringapp.service;

import com.stockmonitoringapp.dto.AuthRequest;
import com.stockmonitoringapp.dto.AuthResponse;
import com.stockmonitoringapp.dto.RegisterRequest;

public interface UserServiceInterface {
	 AuthResponse register(RegisterRequest request);
	 AuthResponse login(AuthRequest request);
}
