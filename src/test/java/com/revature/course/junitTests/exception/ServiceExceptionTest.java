package com.revature.course.junitTests.exception;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.Test;
import com.revature.course.exception.ServiceException;

public class ServiceExceptionTest {

	@Test
	public void testServiceExceptionString() {
		Throwable exception = assertThrows(ServiceException.class, () -> 
	    {
		throw new ServiceException("new ServiceException");
	    });
	    assertEquals("new ServiceException", exception.getMessage());
	}

	@Test
	public void testServiceExceptionStringThrowable() {
		Throwable exception = assertThrows(ServiceException.class, () -> 
	    {
		Exception e = new SQLException();
		throw new ServiceException("new ServiceException",e);
	    });
	    assertEquals("new ServiceException", exception.getMessage());
	}

}
