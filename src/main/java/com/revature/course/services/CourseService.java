package com.revature.course.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.course.dto.CourseDTO;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;
@Service
public interface CourseService {
	public boolean addCourse(CourseDTO courseDTO) throws ServiceException, ValidatorException;
	public List<CourseDTO> viewCourses(String orderBy,String sortBy,int maxRows,int startIndex) throws ServiceException;
	public CourseDTO viewCourseById(int id) throws ServiceException;
	public boolean updateCourse(CourseDTO courseDTO) throws ServiceException, ValidatorException;
	public boolean deleteCourseById(int id) throws ServiceException;
}
