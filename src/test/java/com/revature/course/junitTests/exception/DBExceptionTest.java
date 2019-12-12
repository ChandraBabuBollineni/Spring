package com.revature.course.junitTests.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.Test;

import com.revature.course.exception.DBException;

public class DBExceptionTest {
	@Test
	public void testDBExceptionString() {
		Throwable exception = assertThrows(DBException.class, () -> 
	    {
		throw new DBException("new DBException");
	    });
	    assertEquals("new DBException", exception.getMessage());
	}

	@Test
	public void testDBExceptionStringThrowable() {
		Throwable exception = assertThrows(DBException.class, () -> 
	    {
	    Exception e = new SQLException();
		throw new DBException("new DBException",e);
	    });
	    assertEquals("new DBException", exception.getMessage());
	}

}
