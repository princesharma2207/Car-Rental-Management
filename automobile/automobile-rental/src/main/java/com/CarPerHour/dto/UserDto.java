package com.CarPerHour.dto;

import com.CarPerHour.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String name;
	private String email;
	private UserRole userRole;

}
