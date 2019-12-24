package com.revature.course.dao;

import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.revature.course.exception.DBException;
import com.revature.course.model.Course;


public interface CourseDAO {
	/**
	 * @param course
	 * @return either 0 if failed to insert or 1 if successfully inserted.
	 * @see java.sql.BatchUpdateException
	 * @see java.sql.SQLIntegrityConstraintViolationException
	 * @see java.sql.SQLException
	 * @throws DBException in case of BatchUpdateException occurs or SQLIntegrityConstraintViolationException of unique name,SQLException in case SQL server issues
	 */
	public int addCourse(Course course) throws DBException;
	/**
	 * @param orderBy
	 * @param sortBy
	 * @param maxRows
	 * @param startIndex
	 * @see java.sql.SQLException
	 * @throws DBException in case of any SQLException
	 * @return courses list
	 */
	public List<Course> viewCourses(String orderBy,String sortBy,int maxRows,int startIndex) throws DBException;
	/**
	 * @param id
	 * @see java.sql.SQLException
	 * @throws DBException in case of any SQLException
	 * @return course
	 */
	public Course viewCourseById(int id) throws DBException;
	/**
	 * @param course
	 * @exception IOException - in case if file does not found in specified path
	 * @exception BatchUpdateException - in case if batch update gets failed
	 * @exception SQLException - in case if SQL server is down
	 * @see java.sql.SQLException
	 * @see java.sql.BatchUpdateException
	 * @see java.io.IOException
	 * @throws DBException
	 * @return 1 if successfully updated or 0 if unable to update
	 */
	public int updateCourse(Course course) throws DBException;
	/**
	 * @param id
	 * @see java.sql.SQLException
	 * @exception SQLException - in case if SQL server is down or unable to perform operation
	 * @throws DBException
	 * @return true in case successfully deleted or false if failed 
	 */
	public boolean deleteCourseById(int id) throws DBException;
	/**
	 * @param connection
	 * @see java.sql.SQLException
	 * @exception SQLException - in case if SQL server is down or unable to perform operation
	 * @throws DBException
	 */
	public void rollBackOperation(Connection connection) throws DBException;
}
