package com.revature.course.junitTests.dto;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.course.dto.ReferenceUrlDTO;

public class ReferenceUrlDTOTest {
private ReferenceUrlDTO referenceUrlDTO=new ReferenceUrlDTO();
	@Test
	public void testHashCode() {
		ReferenceUrlDTO referenceUrlDTO1=new ReferenceUrlDTO();
		assertEquals(referenceUrlDTO.hashCode(), referenceUrlDTO1.hashCode());
	}

	@Test
	public void testGetId() {
		referenceUrlDTO.setId(1);
		assertEquals(1, referenceUrlDTO.getId());
	}

	@Test
	public void testGetType() {
		referenceUrlDTO.setType("MAIN CONTENT");
		assertEquals("MAIN CONTENT", referenceUrlDTO.getType());
	}

	@Test
	public void testGetCourseId() {
		referenceUrlDTO.setCourseId(1);
		assertEquals(1, referenceUrlDTO.getCourseId());
	}

	@Test
	public void testGetName() {
		referenceUrlDTO.setName("JAVA");
		assertEquals("JAVA", referenceUrlDTO.getName());
	}

	@Test
	public void testGetUrl() {
		referenceUrlDTO.setUrl("www.app.revature.com/java");
		assertEquals("www.app.revature.com/java", referenceUrlDTO.getUrl());
	}

	@Test
	public void testIsTutorial() {
		referenceUrlDTO.setTutorial(false);
		assertEquals(false, referenceUrlDTO.isTutorial());
	}

	@Test
	public void testGetDescription() {
		referenceUrlDTO.setDescription("It is description.");
		assertEquals("It is description.", referenceUrlDTO.getDescription());
	}

	@Test
	public void testEqualsObject() {
		ReferenceUrlDTO referenceUrlDTO1=new ReferenceUrlDTO();
		 assertTrue(referenceUrlDTO.equals(referenceUrlDTO1));
	}

	@Test
	public void testToString() {
		ReferenceUrlDTO referenceUrlDTO1=new ReferenceUrlDTO();
		 assertEquals(referenceUrlDTO.toString(),referenceUrlDTO1.toString());
	}

}
