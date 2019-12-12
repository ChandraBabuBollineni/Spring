package com.revature.course.junitTests.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.revature.course.exception.ValidatorException;

class ValidatorExceptionTest {

	@Test
	void testValidatorExceptionString() {
		Throwable exception = assertThrows(ValidatorException.class, () -> 
	    {
		throw new ValidatorException("new ValidatorException");
	    });
	    assertEquals("new ValidatorException", exception.getMessage());
	}

	@Test
	void testValidatorExceptionStringThrowable() {
		Throwable exception = assertThrows(ValidatorException.class, () -> 
	    {
		Exception e = new ArithmeticException();
		throw new ValidatorException("new ValidatorException",e);
	    });
	    assertEquals("new ValidatorException", exception.getMessage());
	}
}
