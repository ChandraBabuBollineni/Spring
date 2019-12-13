package com.revature.course.junitTests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import com.revature.course.model.ReferenceArtifacts;

public class ReferenceArtifactsTest {
	private ReferenceArtifacts referenceArtifacts=null;
	private ReferenceArtifacts referenceArtifacts1=null;
	@Before
	public void before()
	{
		referenceArtifacts=new ReferenceArtifacts();
		referenceArtifacts1=new ReferenceArtifacts();
	}
	
	@Test
	public void testHashCode() {
		assertEquals(referenceArtifacts, referenceArtifacts1);
	}

	@Test
	public void testGetId() {
		referenceArtifacts.setId(1);
		assertEquals(1, referenceArtifacts.getId());
	}

	@Test
	public void testGetName() {
		referenceArtifacts.setName("JAVA");
		assertEquals("JAVA", referenceArtifacts.getName());
	}

	@Test
	public void testGetType() {
		referenceArtifacts.setType("MAIN COURSE");
		assertEquals("MAIN COURSE", referenceArtifacts.getType());
	}

	@Test
	public void testGetCourseId() {
		referenceArtifacts.setCourseId(1);
		assertEquals(1, referenceArtifacts.getCourseId());
	}

	@Test
	public void testGetFileName() {
		referenceArtifacts.setFileName("rev-logo-2.png");
		assertEquals("rev-logo-2.png", referenceArtifacts.getFileName());
	}

	@Test
	public void testGetDescription() {
		referenceArtifacts.setDescription("It is test description");
		assertEquals("It is test description", referenceArtifacts.getDescription());
	}

	@Test
	public void testEqualsObject() {
		 assertTrue(referenceArtifacts.equals(referenceArtifacts1));
	}

	@Test
	public void testToString() {
		assertEquals(referenceArtifacts.toString(), referenceArtifacts1.toString());
	}
	
	
}
