package com.revature.course.junitTests.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revature.course.model.CourseUsers;

public class CourseUsersTest {
	private CourseUsers courseUsers=null;
	private CourseUsers courseUsers1=null;
	
	@Before
	public void before()
	{
		courseUsers=new CourseUsers();
		courseUsers1=new CourseUsers();
	}

	@Test
	public void testHashCode() {
		assertEquals(courseUsers.hashCode(), courseUsers1.hashCode());
	}

	@Test
	public void testGetId() {
		courseUsers.setId(1);
		assertEquals(1, courseUsers.getId());
	}

	@Test
	public void testGetName() {
		courseUsers.setName("chandra");
		assertEquals("chandra", courseUsers.getName());
	}

	@Test
	public void testGetEmail() {
		courseUsers.setEmail("chandra@gmail.com");
		assertEquals("chandra@gmail.com", courseUsers.getEmail());
	}

	@Test
	public void testEqualsObject() {
		 assertTrue(courseUsers.equals(courseUsers1));
	}

	@Test
	public void testToString() {
		assertEquals(courseUsers.toString(),courseUsers1.toString());
	}

	@Test
	public void testcourseUsers() {
		courseUsers.setId(1);
		courseUsers.setName("chandra");
		courseUsers.setEmail("chandra@gmail.com");
		assertNotNull(courseUsers);
	}

}
