package com.revature.course.mockmvcTests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.sql.Connection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import com.revature.course.configuration.DBUtils;
import com.revature.course.dao.impl.CourseDAOImpl;
import com.revature.course.dto.CategoryDTO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.LevelDTO;
import com.revature.course.exception.DBException;

@RunWith(MockitoJUnitRunner.Silent.class) 
class CourseDAOImplTest {
	
	public CourseDAOImpl courseDAO=new CourseDAOImpl();
	
	@Test
	public void testAddCourse() {
		CourseDTO courseDTO=new CourseDTO();
		CategoryDTO category=new CategoryDTO();
		category.setId(1);
		courseDTO.setCategory(category);
		LevelDTO level=new LevelDTO();
		level.setId(1);
		courseDTO.setLevel(level);
		
		//courseDAO.addCourse(courseDTO);
	}

	@Test
	public void testViewCourses() {
		List<CourseDTO> coursesList=courseDAO.viewCourses("c.id", "desc", 2, 0);
		assertNotNull(coursesList);
	}

	@Test
	public void testViewCourseById() {
		int id=6;
		CourseDTO coursesList=courseDAO.viewCourseById(id);
		assertNotNull(coursesList);
	}

	@Test
	public void testViewCourseByIdInvalid() {					
		int id=1;
		CourseDTO coursesList=courseDAO.viewCourseById(id);
		assertNull(coursesList);
	}

	
	@Test
	public void testUpdateCourse() {
		
	}

	@Test
	public void testDeleteCourseById() {
	/*	int id=5;
		boolean status=courseDAO.deleteCourseById(id);
		assertTrue(status);*/
	}
	
	@Test
	public void testDeleteCourseByIdInvalid() {
		/* exceptionRule.expect(DBException.class);
		int id=1;
		boolean status=courseDAO.deleteCourseById(id);
		assertFalse(status);*/
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
		try {
		String name="ghfdsdd";
		status=courseDAO.checkName(name);
	}
		catch(DBException ex) {}
		assertTrue(status);
		}
	
	@Test
	public void testCheckNameInvalid() {
		String name="Invalid";
		boolean status=courseDAO.checkName(name);
		assertFalse(status);
	}

}
