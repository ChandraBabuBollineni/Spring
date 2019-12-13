package com.revature.course.junitTests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import com.revature.course.model.ReferenceUrl;

public class ReferenceUrlTest {
	private ReferenceUrl referenceUrl=null;
	private ReferenceUrl referenceUrl1=null;
	
	@Before
	public void before()
	{
		referenceUrl=new ReferenceUrl();
		referenceUrl1=new ReferenceUrl();
	}
	
	@Test
	public void testHashCode() {
		assertEquals(referenceUrl.hashCode(), referenceUrl1.hashCode());
	}

	@Test
	public void testGetId() {
		referenceUrl.setId(1);
		assertEquals(1, referenceUrl.getId());
	}

	@Test
	public void testGetType() {
		referenceUrl.setType("MAIN CONTENT");
		assertEquals("MAIN CONTENT", referenceUrl.getType());
	}

	@Test
	public void testGetCourseId() {
		referenceUrl.setCourseId(1);
		assertEquals(1, referenceUrl.getCourseId());
	}

	@Test
	public void testGetName() {
		referenceUrl.setName("JAVA");
		assertEquals("JAVA", referenceUrl.getName());
	}

	@Test 
	public void testGetUrl() {
		referenceUrl.setUrl("www.app.revature.com/java");
		assertEquals("www.app.revature.com/java", referenceUrl.getUrl());
	}

	@Test
	public void testIsTutorial() {
		referenceUrl.setTutorial(false);
		assertEquals(false, referenceUrl.isTutorial());
	}

	@Test
	public void testGetDescription() {
		referenceUrl.setDescription("It is description.");
		assertEquals("It is description.", referenceUrl.getDescription());
	}

	@Test
	public void testEqualsObject() {
		 assertTrue(referenceUrl.equals(referenceUrl1));
	}

	@Test
	public void testToString() {
		 assertEquals(referenceUrl.toString(),referenceUrl1.toString());
	}

	@Test
	public void testGetClass() {
		assertEquals(referenceUrl.getClass(), referenceUrl1.getClass());
	}
}
