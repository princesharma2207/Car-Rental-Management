package com.CarPerHour.exception;


	public class UserNotFoundException extends RuntimeException {
		public UserNotFoundException(String message) {
			super(message); //invokes the constructor of the parent class
		
	}
}
