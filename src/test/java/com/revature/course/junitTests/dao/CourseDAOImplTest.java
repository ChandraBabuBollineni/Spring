package com.revature.course.junitTests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.sql.Connection;
import java.util.List;
import org.junit.Test;
import com.revature.course.configuration.DBUtils;
import com.revature.course.exception.DBException;
import com.revature.course.junitTests.util.CourseUtil;
import com.revature.course.model.Course;

public class CourseDAOImplTest {
/*	
	private CourseDAOImpl courseDAO=new CourseDAOImpl();	
	@Test
	public void testAddCourse() {
		Throwable exception = assertThrows(DBException.class, () -> 
	    {
		Course course=CourseUtil.getCourseDetails();
		course.setName("c#");
		int result=courseDAO.addCourse(course);
		System.out.println(result);
		assertTrue(result!=0);
	    });
	    assertEquals("Unable to add courses", exception.getMessage());
	}

	@Test
	public void testViewCourses() {
		List<Course> coursesList=courseDAO.viewCourses("c.id", "desc", 2, 0);
		assertNotNull(coursesList);
	}

	@Test
	public void testViewCourseById() {
		Course coursesList=courseDAO.viewCourseById(23);
		assertNotNull(coursesList);
	}

	@Test
	public void testViewCourseByIdInvalid() {					
		int id=1;
		Course coursesList=courseDAO.viewCourseById(id);
		assertNull(coursesList);
	}

	
	@Test
	public void testUpdateCourse() {
		Course course=CourseUtil.getCourseDetails();
		course.setId(23);
		int result=courseDAO.updateCourse(course);
		assertTrue(result!=0);
	}

	@Test()
	public void testDeleteCourseById() {
		Throwable exception = assertThrows(DBException.class, () -> 
	    {
		int id=11;
		boolean status=courseDAO.deleteCourseById(id);
		assertTrue(status);
	    });
	    assertEquals("Unable to delete course", exception.getMessage());
	}
	
	@Test(expected=DBException.class)
	public void testDeleteCourseByIdInvalid() {
		int id=1;
		boolean status=courseDAO.deleteCourseById(id);
		assertFalse(status);
	}

	@Test
	public void testRollBackOperation() {
		Throwable exception = assertThrows(DBException.class, () -> 
	    {
	    	Connection connection = DBUtils.getConnection();
		courseDAO.rollBackOperation(connection); 
	    });
	    assertEquals("Unable to add course", exception.getMessage());
	}

	@Test
	public void testCheckName() {
		boolean status = false;
		String name="java";
		status=courseDAO.checkName(name);
		assertTrue(status);
		}
	
	@Test
	public void testCheckNameInvalid() {
		String name="Invalid";
		boolean status=courseDAO.checkName(name);
		assertFalse(status);
	}
*/
}
