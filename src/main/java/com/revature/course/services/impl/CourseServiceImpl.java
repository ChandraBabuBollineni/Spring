package com.revature.course.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.course.dao.CourseDAO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;
import com.revature.course.services.CourseService;
import com.revature.course.services.ReferenceArtifactsService;
import com.revature.course.services.ReferenceUrlService;
import com.revature.course.util.CourseDTOValidator;
import com.revature.course.util.ReferenceArtifactsDTOValidator;
import com.revature.course.util.ReferenceUrlDTOValidator;


@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseDAO courseRepository;

	@Autowired
	private ReferenceArtifactsService referenceArtifactsServices;

	@Autowired
	private ReferenceUrlService referenceUrlServices;

	public boolean addCourse(CourseDTO courseDTO) throws ServiceException, ValidatorException {
		CourseDTOValidator courseDTOValidator = new CourseDTOValidator();
		courseDTOValidator.courseDtoValidator(courseDTO);
		ReferenceArtifactsDTOValidator referenceArtifactsDTOValidator=new ReferenceArtifactsDTOValidator();
		referenceArtifactsDTOValidator.referenceArtifactsDTOListValidator(courseDTO.getReferenceArtifactsId());
		ReferenceUrlDTOValidator referenceUrlDTOValidator=new ReferenceUrlDTOValidator();
		referenceUrlDTOValidator.referenceUrlDTOListValidator(courseDTO.getRefernceUrlId());
	
		// File fs=new File("C:/Users/Revature1/Downloads/jaf-1_1_1.zip");
		File fs = new File("C:/Users/Revature1/Downloads/rev-logo-2.png");
		try {
			FileInputStream fis = new FileInputStream(fs);
		} catch (FileNotFoundException e) {
			throw new ServiceException("file not found", e);
		}
		String fileName = fs.getName();
		courseDTO.setIcon(fs);
		courseDTO.setIconName(fileName);
		boolean status = courseRepository.checkName(courseDTO.getName());
		if (status) {
			throw new ServiceException("Name already exists.");
		}
		status = true;
		int result = 0;
		result = courseRepository.addCourse(courseDTO);
		if (result == 0) {
			status = false;
		}
		return status;
	}

	public List<CourseDTO> viewCourses(String orderBy,String sortBy,int maxRows,int startIndex) throws ServiceException {
		List<CourseDTO> courseList = courseRepository.viewCourses(orderBy,sortBy,maxRows,startIndex);
		if (!courseList.isEmpty()) {
			for (CourseDTO courseListDto : courseList) {
				List<ReferenceArtifactsDTO> referenceArtifactsDTOList = referenceArtifactsServices
						.viewReferenceArtifactsById(courseListDto.getId());
				courseListDto.setReferenceArtifactsId(referenceArtifactsDTOList);

				List<ReferenceUrlDTO> referenceUrlDTOList = referenceUrlServices
						.viewReferenceUrlById(courseListDto.getId());
				courseListDto.setRefernceUrlId(referenceUrlDTOList);
			}
		} else {
			throw new ServiceException("No records available.");
		}
		return courseList;
	}

	public CourseDTO viewCourseById(int id) throws ServiceException {
		CourseDTO course = null;
		course = courseRepository.viewCourseById(id);
		if (course != null) {
			List<ReferenceArtifactsDTO> referenceArtifactsDTOList = referenceArtifactsServices
					.viewReferenceArtifactsById(course.getId());
			course.setReferenceArtifactsId(referenceArtifactsDTOList);
			List<ReferenceUrlDTO> referenceUrlDTOList = referenceUrlServices.viewReferenceUrlById(course.getId());
			course.setRefernceUrlId(referenceUrlDTOList);
		} else {
			throw new ServiceException("course not available");
		}

		return course;
	}

	public boolean updateCourse(CourseDTO courseDTO) throws ServiceException, ValidatorException {
		boolean status = true;
		CourseDTOValidator courseDTOValidator = new CourseDTOValidator();
		courseDTOValidator.courseDtoValidator(courseDTO);
		if( viewCourseById(courseDTO.getId()).getId()==courseDTO.getId()) {
		int result = courseRepository.updateCourse(courseDTO);
		if (result == 0) {
			status = false;
			throw new ServiceException("unable to update course");
		}
		return status;
	}
		else 
		{
			throw new ServiceException("details not available.");
		}
	}
	public boolean deleteCourseById(int id) throws ServiceException {
		return courseRepository.deleteCourseById(id);
	}

}
