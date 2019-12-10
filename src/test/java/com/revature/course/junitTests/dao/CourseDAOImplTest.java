package com.revature.course.junitTests.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.util.List;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.revature.course.configuration.DBUtils;
import com.revature.course.dao.CourseDAO;
import com.revature.course.dto.CategoryDTO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.LevelDTO;
import com.revature.course.exception.DBException;

@RunWith(SpringRunner.class)
@SpringBootTest
class CourseDAOImplTest {
	@Autowired
	private CourseDAO courseDAO;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	void testAddCourse() {
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
	void testViewCourses() {
		List<CourseDTO> coursesList=courseDAO.viewCourses();
		assertNotNull(coursesList);
	}

	@Test
	void testViewCourseById() {
		int id=6;
		CourseDTO coursesList=courseDAO.viewCourseById(id);
		assertNotNull(coursesList);
	}

	@Test
	void testViewCourseByIdInvalid() {
		int id=1;
		CourseDTO coursesList=courseDAO.viewCourseById(id);
		assertNull(coursesList);
	}

	
	@Test
	void testUpdateCourse() {
		
	}

	@Test
	void testDeleteCourseById() {
	/*	int id=5;
		boolean status=courseDAO.deleteCourseById(id);
		assertTrue(status);*/
	}
	
	@Test
	void testDeleteCourseByIdInvalid() {
		/* exceptionRule.expect(DBException.class);
		int id=1;
		boolean status=courseDAO.deleteCourseById(id);
		assertFalse(status);*/
	}

	@Test()
	void testRollBackOperation() {
		Connection connection = DBUtils.getConnection();
		courseDAO.rollBackOperation(connection); 
	}

	@Test
	void testCheckName() {
		boolean status = false;
		try {
		String name="ghfdsdd";
		status=courseDAO.checkName(name);
	}
		catch(DBException ex) {}
		assertTrue(status);
		}
	
	@Test
	void testCheckNameInvalid() {
		String name="Invalid";
		boolean status=courseDAO.checkName(name);
		assertFalse(status);
	}

}
