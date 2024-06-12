package com.CarPerHour.dto;

import com.CarPerHour.enums.UserRole;

import lombok.Data;
 
@Data
 
public class AuthenticationResponse {
 
	  private String jwt;
	    private UserRole userRole;
	    private Long userId;
}