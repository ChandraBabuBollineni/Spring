package com.revature.course.junitTests.util;

import org.modelmapper.ModelMapper;

import com.revature.course.dto.CourseDTO;
import com.revature.course.model.Course;

public class CourseUtil {
	public static Course getCourseDetails()
	{
	ModelMapper modelMapper=new ModelMapper();
CourseDTO courseDTO=CourseDTOUtil.getCourseDTODetails();
modelMapper.getConfiguration().setAmbiguityIgnored(true);
Course course = modelMapper.map(courseDTO, Course.class);
return course;
}
}