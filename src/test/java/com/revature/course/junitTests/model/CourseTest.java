package com.revature.course.junitTests.model;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.revature.course.model.Category;
import com.revature.course.model.Course;
import com.revature.course.model.CourseUsers;
import com.revature.course.model.Level;
import com.revature.course.model.ReferenceArtifacts;
import com.revature.course.model.ReferenceUrl;

public class CourseTest {

	private Course course=null;
	private Course course1=null;
	
	@Before
	public void before()
	{
		course=new Course();
		course1=new Course();
	}
	
	@Test
	public void testHashCode() {
		assertTrue( course.hashCode()==course1.hashCode() );
	}

	@Test
	public void testGetId() {
		course.setId(1);
		assertEquals(1, course.getId());
	}

	@Test
	public void testGetName() {
		course.setName("JAVA");
		assertEquals("JAVA", course.getName());
	}

	@Test
	public void testGetLevel() {
		Level level=new Level();
		level.setId(100);
		course.setLevel(level);
		assertEquals(100, course.getLevel().getId());
	}

	@Test
	public void testGetCategory() {
		Category category=new Category();
		category.setId(1000);
		course.setCategory(category);
		assertEquals(1000, course.getCategory().getId());
	}

	@Test
	public void testGetTags() {
		course.setTags("JAVA,JAVA PROGRAMMING");
		assertEquals("JAVA,JAVA PROGRAMMING", course.getTags());
	}

	@Test
	public void testGetSlug() {
		course.setSlug("www.app.revature.com/admin");
		assertEquals("www.app.revature.com/admin", course.getSlug());
	}

	@Test
	public void testIsLevelOverRide() {
		course.setLevelOverRide(true);
		assertEquals(true, course.isLevelOverRide());
	}

	@Test
	public void testGetEnrollmentPoint() {
		course.setEnrollmentPoint(100);
		assertEquals(100, course.getEnrollmentPoint());
	}

	@Test
	public void testGetCompletionPoint() {
		course.setCompletionPoint(120);
		assertEquals(120, course.getCompletionPoint());
	}

	@Test
	public void testIsPreSignUp() {
		course.setPreSignUp(true);
		assertEquals(true, course.isPreSignUp());
	}

	@Test
	public void testIsLoggedInViaSlug() {
		course.setLoggedInViaSlug(false);
		assertEquals(false, course.isLoggedInViaSlug());
	}

	@Test
	public void testGetDescription() {
		course.setDescription("It is description.");
		assertEquals("It is description.", course.getDescription());
	}

	@Test
	public void testGetMetaKeyword() {
		course.setMetaKeyword("It is meta key word");
		assertEquals("It is meta key word", course.getMetaKeyword());
	}

	@Test
	public void testGetMetaDescription() {
		course.setMetaDescription("It is meta description.");
		assertEquals("It is meta description.", course.getMetaDescription());
	}

	@Test
	public void testGetReferenceArtifactsId() {
		List<ReferenceArtifacts> referenceArtifactsList=new ArrayList<ReferenceArtifacts>();
		ReferenceArtifacts referenceArtifacts=new ReferenceArtifacts();
		referenceArtifacts.setId(1);
		referenceArtifacts.setName("JAVA");
		referenceArtifacts.setType("Course Content");
		referenceArtifacts.setCourseId(1);
		referenceArtifacts.setFileName("java.zip");
		referenceArtifacts.setDescription("It is description.");
		referenceArtifactsList.add(referenceArtifacts);
		course.setReferenceArtifactsId(referenceArtifactsList);
		assertEquals(referenceArtifactsList, course.getReferenceArtifactsId());
	}

	@Test
	public void testGetRefernceUrlId() {
		List<ReferenceUrl> referenceUrlList=new ArrayList<>();
		ReferenceUrl referenceUrl=new ReferenceUrl();
		referenceUrl.setId(1);
		referenceUrl.setCourseId(1);
		referenceUrl.setDescription("It is description.");
		referenceUrl.setName("JAVA");
		referenceUrl.setTutorial(true);
		referenceUrl.setType("Course Content");
		referenceUrl.setUrl("www.app.revature.com/java");
		referenceUrlList.add(referenceUrl);
		course.setRefernceUrlId(referenceUrlList);
		assertEquals(referenceUrlList, course.getRefernceUrlId());
	}

	@Test
	public void testGetCreatedBy() {
		CourseUsers users=new CourseUsers();
		users.setId(1);
		users.setName("Chandra");
		users.setEmail("chandra@gmail.com");
		course.setCreatedBy(users);
		assertEquals(users, course.getCreatedBy());		
	}

	@Test
	public void testGetIcon() {
		File icon=new File("C:\\Users\\Revature1\\Downloads\\rev-logo-2.png");
		course.setIcon(icon);
		assertEquals(icon, course.getIcon());
	}

	@Test
	public void testGetIconName() {
		course.setIconName("rev-logo-2.png");
		assertEquals("rev-logo-2.png", course.getIconName());
	}

	@Test
	public void testIsStatus() {
		course.setStatus(true);
		assertEquals(true, course.isStatus());
	}

	@Test
	public void testGetVersion() {
		course.setVersion(1);
		assertEquals(1, course.getVersion());
	}

	@Test
	public void testEqualsObject() {
		 assertTrue(course.equals(course1));
	}


	@Test
	public void testToString() {
		assertEquals(course.toString(), course1.toString());;
	}

	@Test
	public void testCourse() {
		System.out.println(course.getId());
	}

}
