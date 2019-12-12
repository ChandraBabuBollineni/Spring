package com.revature.course.junitTests.dto;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.course.dto.UsersDTO;

public class UsersDTOTest {
private UsersDTO usersDTO=new UsersDTO();
	@Test
	public void testHashCode() {
		UsersDTO usersDTO1=new UsersDTO();
		assertEquals(usersDTO.hashCode(), usersDTO1.hashCode());
	}

	@Test
	public void testGetId() {
		usersDTO.setId(1);
		assertEquals(1, usersDTO.getId());
	}

	@Test
	public void testGetName() {
		usersDTO.setName("chandra");
		assertEquals("chandra", usersDTO.getName());
	}

	@Test
	public void testGetEmail() {
		usersDTO.setEmail("chandra@gmail.com");
		assertEquals("chandra@gmail.com", usersDTO.getEmail());
	}

	@Test
	public void testEqualsObject() {
		UsersDTO usersDTO1=new UsersDTO();
		 assertTrue(usersDTO.equals(usersDTO1));
	}

	@Test
	public void testToString() {
		UsersDTO usersDTO1=new UsersDTO();
		assertEquals(usersDTO.toString(),usersDTO1.toString());
	}

	@Test
	public void testUsersDTO() {
		usersDTO.setId(1);
		usersDTO.setName("chandra");
		usersDTO.setEmail("chandra@gmail.com");
		assertNotNull(usersDTO);
	}

}
