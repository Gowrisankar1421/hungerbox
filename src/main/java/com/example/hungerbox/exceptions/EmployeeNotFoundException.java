package com.example.hungerbox.exceptions;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException{
	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
