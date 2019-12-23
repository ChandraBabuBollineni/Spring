package com.revature.course.services;

import java.util.List;
import com.revature.course.dto.CourseDTO;
import com.revature.course.exception.DBException;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;

public interface CourseService {
	/**
	 * @param courseDTO
	 * @see com.revature.course.dto.CourseDTO
	 * @return either 0 if failed to insert or 1 if successfully inserted.
	 * @see com.revature.course.exception.DBException
	 * @see com.revature.course.exception.ValidatorException
	 * @see com.revature.course.exception.ServiceException
	 * @throws DBException in case of BatchUpdateException occurs or SQLIntegrityConstraintViolationException of unique name,SQLException in case SQL server issues
	 * @throws ValidatorException in case invalid details passed
	 * @throws ServiceException in case exception occurs in Service Layer
	 * @return true if successfully added or false if failed to add
	 */
	public boolean addCourse(CourseDTO courseDTO) throws ServiceException, ValidatorException,DBException;
	/**
	 * @param orderBy
	 * @param sortBy
	 * @param maxRows
	 * @param startIndex
	 * @see com.revature.course.exception.DBException
	 * @see com.revature.course.exception.ServiceException
	 * @throws DBException in case of SQLException in case SQL server issues
	 * @throws ServiceException in case exception occurs in Service Layer
	 * @return CourseDTOList
	 */
	public List<CourseDTO> viewCourses(String orderBy,String sortBy,int maxRows,int startIndex) throws ServiceException,DBException;
	/**
	 * @param id
	 * @see com.revature.course.exception.DBException
	 * @see com.revature.course.exception.ServiceException
	 * @throws DBException in case of SQLException in case SQL server issues
	 * @throws ServiceException in case exception occurs in Service Layer
	 * @return CourseDTO
	 */
	public CourseDTO viewCourseById(int id) throws ServiceException,DBException;
	/**
	 * @param courseDTO
	 * @see com.revature.course.dto.CourseDTO
	 * @see com.revature.course.exception.DBException
	 * @see com.revature.course.exception.ServiceException
	 * @see com.revature.course.exception.ValidatorException
	 * @throws DBException in case of SQLException in case SQL server issues
	 * @throws ServiceException in case exception occurs in Service Layer
	 * @return true in case successfully added or false if failed to update
	 */
	public boolean updateCourse(CourseDTO courseDTO) throws ServiceException, ValidatorException,DBException;
	/**
	 * @param id
	 * @see com.revature.course.exception.DBException
	 * @see com.revature.course.exception.ServiceException
	 * @throws DBException in case of SQLException in case SQL server issues
	 * @throws ServiceException in case exception occurs in Service Layer
	 * @return true in case successfully deleted or false if failed to delete
	 */
	public boolean deleteCourseById(int id) throws ServiceException,DBException;
}
