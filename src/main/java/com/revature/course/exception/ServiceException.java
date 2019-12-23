package com.revature.course.exception;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * 
 * @param message - String
 */
	public ServiceException(String message) {
		super(message);
	}
/**
 * 
 * @param message - String
 * @param Throwable
 * @see java.lang.Throwable
 */
	public ServiceException(String message, Throwable t) {
		super(message, t);
	}
}
