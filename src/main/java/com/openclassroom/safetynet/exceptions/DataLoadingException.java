package com.openclassroom.safetynet.exceptions;

public class DataLoadingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataLoadingException(String message) {
		super(message);
	}
}