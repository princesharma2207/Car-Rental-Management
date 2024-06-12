package com.CarPerHour.services.auth;

import org.springframework.stereotype.Service;

import com.CarPerHour.dto.SignupRequest;
import com.CarPerHour.dto.UserDto;
import com.CarPerHour.entity.User;
import com.CarPerHour.enums.UserRole;

@Service
public interface AuthService {
	UserDto createCustomer(SignupRequest signupRequest);
	public boolean hasCustomerWithEmail(String email);
	
}
