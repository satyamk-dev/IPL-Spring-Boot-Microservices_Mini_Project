package com.nt.exception;

public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException(String message) {
		super(message);
	}
}
