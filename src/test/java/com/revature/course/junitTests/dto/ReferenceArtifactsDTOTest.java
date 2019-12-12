package com.revature.course.junitTests.dto;

import static org.junit.Assert.*;
import org.junit.Test;
import com.revature.course.dto.ReferenceArtifactsDTO;

public class ReferenceArtifactsDTOTest {
private ReferenceArtifactsDTO referenceArtifactsDTO=new ReferenceArtifactsDTO();
	@Test
	public void testHashCode() {
		ReferenceArtifactsDTO referenceArtifactsDTO1=new ReferenceArtifactsDTO();
		assertEquals(referenceArtifactsDTO, referenceArtifactsDTO1);
	}

	@Test
	public void testGetId() {
		referenceArtifactsDTO.setId(1);
		assertEquals(1, referenceArtifactsDTO.getId());
	}

	@Test
	public void testGetName() {
		referenceArtifactsDTO.setName("JAVA");
		assertEquals("JAVA", referenceArtifactsDTO.getName());
	}

	@Test
	public void testGetType() {
		referenceArtifactsDTO.setType("MAIN COURSE");
		assertEquals("MAIN COURSE", referenceArtifactsDTO.getType());
	}

	@Test
	public void testGetCourseId() {
		referenceArtifactsDTO.setCourseId(1);
		assertEquals(1, referenceArtifactsDTO.getCourseId());
	}

	@Test
	public void testGetFileName() {
		referenceArtifactsDTO.setFileName("rev-logo-2.png");
		assertEquals("rev-logo-2.png", referenceArtifactsDTO.getFileName());
	}

	@Test
	public void testGetDescription() {
		referenceArtifactsDTO.setDescription("It is test description");
		assertEquals("It is test description", referenceArtifactsDTO.getDescription());
	}

	@Test
	public void testEqualsObject() {
		ReferenceArtifactsDTO referenceArtifactsDTO1=new ReferenceArtifactsDTO();
		 assertTrue(referenceArtifactsDTO.equals(referenceArtifactsDTO1));
	}

	@Test
	public void testToString() {
		ReferenceArtifactsDTO referenceArtifactsDTO1=new ReferenceArtifactsDTO();
		assertEquals(referenceArtifactsDTO.toString(), referenceArtifactsDTO1.toString());
	}

}
