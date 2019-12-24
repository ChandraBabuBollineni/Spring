package com.revature.course.exception;

public class ValidatorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * 
 * @param message - String
 */
	public ValidatorException(String message) {
		super(message);
	}
/**
 * 
 * @param message - String
 * @param Throwable
 * @see java.lang.Throwable
 */
	public ValidatorException(String message, Throwable t) {
		super(message, t);
	}
}
