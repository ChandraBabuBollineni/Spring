package com.revature.course.junitTests.dto;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.course.dto.CategoryDTO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.LevelDTO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.dto.UsersDTO;

public class CourseDTOTest {
private final CourseDTO courseDTO=new CourseDTO();
private final CourseDTO courseDTO1=new CourseDTO();

	@Test
	public void testHashCode() {
		assertTrue( courseDTO.hashCode()==courseDTO1.hashCode() );
	}

	@Test
	public void testGetId() {
		courseDTO.setId(1);
		assertEquals(1, courseDTO.getId());
	}

	@Test
	public void testGetName() {
		courseDTO.setName("JAVA");
		assertEquals("JAVA", courseDTO.getName());
	}

	@Test
	public void testGetLevel() {
		LevelDTO levelDTO=new LevelDTO();
		levelDTO.setId(100);
		courseDTO.setLevel(levelDTO);
		assertEquals(100, courseDTO.getLevel().getId());
	}

	@Test
	public void testGetCategory() {
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.setId(1000);
		courseDTO.setCategory(categoryDTO);
		assertEquals(1000, courseDTO.getCategory().getId());
	}

	@Test
	public void testGetTags() {
		courseDTO.setTags("JAVA,JAVA PROGRAMMING");
		assertEquals("JAVA,JAVA PROGRAMMING", courseDTO.getTags());
	}

	@Test
	public void testGetSlug() {
		courseDTO.setSlug("www.app.revature.com/admin");
		assertEquals("www.app.revature.com/admin", courseDTO.getSlug());
	}

	@Test
	public void testIsLevelOverRide() {
		courseDTO.setLevelOverRide(true);
		assertEquals(true, courseDTO.isLevelOverRide());
	}

	@Test
	public void testGetEnrollmentPoint() {
		courseDTO.setEnrollmentPoint(100);
		assertEquals(100, courseDTO.getEnrollmentPoint());
	}

	@Test
	public void testGetCompletionPoint() {
		courseDTO.setCompletionPoint(120);
		assertEquals(120, courseDTO.getCompletionPoint());
	}

	@Test
	public void testIsPreSignUp() {
		courseDTO.setPreSignUp(true);
		assertEquals(true, courseDTO.isPreSignUp());
	}

	@Test
	public void testIsLoggedInViaSlug() {
		courseDTO.setLoggedInViaSlug(false);
		assertEquals(false, courseDTO.isLoggedInViaSlug());
	}

	@Test
	public void testGetDescription() {
		courseDTO.setDescription("It is description.");
		assertEquals("It is description.", courseDTO.getDescription());
	}

	@Test
	public void testGetMetaKeyword() {
		courseDTO.setMetaKeyword("It is meta key word");
		assertEquals("It is meta key word", courseDTO.getMetaKeyword());
	}

	@Test
	public void testGetMetaDescription() {
		courseDTO.setMetaDescription("It is meta description.");
		assertEquals("It is meta description.", courseDTO.getMetaDescription());
	}

	@Test
	public void testGetReferenceArtifactsId() {
		List<ReferenceArtifactsDTO> referenceArtifactsDTOList=new ArrayList<ReferenceArtifactsDTO>();
		ReferenceArtifactsDTO referenceArtifactsDTO=new ReferenceArtifactsDTO();
		referenceArtifactsDTO.setId(1);
		referenceArtifactsDTO.setName("JAVA");
		referenceArtifactsDTO.setType("Course Content");
		referenceArtifactsDTO.setCourseId(1);
		referenceArtifactsDTO.setFileName("java.zip");
		referenceArtifactsDTO.setDescription("It is description.");
		referenceArtifactsDTOList.add(referenceArtifactsDTO);
		courseDTO.setReferenceArtifactsId(referenceArtifactsDTOList);
		assertEquals(referenceArtifactsDTOList, courseDTO.getReferenceArtifactsId());
	}

	@Test
	public void testGetRefernceUrlId() {
		List<ReferenceUrlDTO> referenceUrlDTOList=new ArrayList<>();
		ReferenceUrlDTO referenceUrlDTO=new ReferenceUrlDTO();
		referenceUrlDTO.setId(1);
		referenceUrlDTO.setCourseId(1);
		referenceUrlDTO.setDescription("It is description.");
		referenceUrlDTO.setName("JAVA");
		referenceUrlDTO.setTutorial(true);
		referenceUrlDTO.setType("Course Content");
		referenceUrlDTO.setUrl("www.app.revature.com/java");
		referenceUrlDTOList.add(referenceUrlDTO);
		courseDTO.setRefernceUrlId(referenceUrlDTOList);
		assertEquals(referenceUrlDTOList, courseDTO.getRefernceUrlId());
	}

	@Test
	public void testGetCreatedBy() {
		UsersDTO usersDTO=new UsersDTO();
		usersDTO.setId(1);
		usersDTO.setName("Chandra");
		usersDTO.setEmail("chandra@gmail.com");
		courseDTO.setCreatedBy(usersDTO);
		assertEquals(usersDTO, courseDTO.getCreatedBy());		
	}

	@Test
	public void testGetIcon() {
		File icon=new File("C:\\Users\\Revature1\\Downloads\\rev-logo-2.png");
		courseDTO.setIcon(icon);
		assertEquals(icon, courseDTO.getIcon());
	}

	@Test
	public void testGetIconName() {
		courseDTO.setIconName("rev-logo-2.png");
		assertEquals("rev-logo-2.png", courseDTO.getIconName());
	}

	@Test
	public void testIsStatus() {
		courseDTO.setStatus(true);
		assertEquals(true, courseDTO.isStatus());
	}

	@Test
	public void testGetVersion() {
		courseDTO.setVersion(1);
		assertEquals(1, courseDTO.getVersion());
	}

	@Test
	public void testEqualsObject() {
		 assertTrue(courseDTO.equals(courseDTO1));
	}


	@Test
	public void testToString() {
		assertEquals(courseDTO.toString(), courseDTO1.toString());;
	}

	@Test
	public void testCourseDTO() {
		System.out.println(courseDTO.getId());
	}

}
