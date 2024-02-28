package com.cucumber.framework.exception;

/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */
public class NoSutiableDriverFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSutiableDriverFoundException(String message) {
		super(message);
	}
	
	public NoSutiableDriverFoundException(){
		this("");
	}

}
