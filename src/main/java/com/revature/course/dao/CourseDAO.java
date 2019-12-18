package com.revature.course.dao;

import java.sql.Connection;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.model.Course;

@Repository
public interface CourseDAO {
	public int addCourse(Course course);

	public List<Course> viewCourses(String orderBy,String sortBy,int maxRows,int startIndex);

	public Course viewCourseById(int id);

	public int updateCourse(Course course);

	public boolean deleteCourseById(int id);

	public void rollBackOperation(Connection connection);

	public boolean checkName(String name);
}
