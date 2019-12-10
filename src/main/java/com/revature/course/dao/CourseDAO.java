package com.revature.course.dao;

import java.sql.Connection;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.dto.CourseDTO;

@Repository
public interface CourseDAO {
	public int addCourse(CourseDTO courseDTO);

	public List<CourseDTO> viewCourses(String orderBy,String sortBy,int maxRows,int startIndex);

	public CourseDTO viewCourseById(int id);

	public int updateCourse(CourseDTO courseDTO);

	public boolean deleteCourseById(int id);

	public void rollBackOperation(Connection connection);

	public boolean checkName(String name);
}
