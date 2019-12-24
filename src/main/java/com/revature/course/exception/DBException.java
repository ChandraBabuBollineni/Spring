package com.revature.course.exception;

public class DBException extends Exception {
	/**
		 * 
		 */
	private static final long serialVersionUID = 7940028551428187302L;
/**
 * 
 * @param message - String
 */
	public DBException(String message) {
		super(message);
	}
/**
 * 
 * @param message - String
 * @param Throwable
 * @see java.lang.Throwable
 */
	public DBException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
