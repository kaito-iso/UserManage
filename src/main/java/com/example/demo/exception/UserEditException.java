package com.example.demo.exception;

import lombok.Getter;

@Getter
public class UserEditException extends RuntimeException {

	private final String fieldName;

	public UserEditException(String message, String fieldName) {
		super(message);
		this.fieldName = fieldName;
	}

}