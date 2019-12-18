package com.revature.course.services.impl;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.course.dao.CourseDAO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;
import com.revature.course.model.Course;
import com.revature.course.services.CourseService;
import com.revature.course.services.ReferenceArtifactsService;
import com.revature.course.services.ReferenceUrlService;
import com.revature.course.util.CourseDTOValidator;
import com.revature.course.util.ReferenceArtifactsDTOValidator;
import com.revature.course.util.ReferenceUrlDTOValidator;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDAO courseRepository;

	@Autowired
	private ReferenceArtifactsService referenceArtifactsServices;

	@Autowired
	private ReferenceUrlService referenceUrlServices;
	
	private ModelMapper modelMapper=new ModelMapper();
	
	Course course=new Course();
	
	
	public boolean addCourse(CourseDTO courseDTO) throws ServiceException, ValidatorException {
		CourseDTOValidator courseDTOValidator = new CourseDTOValidator();
		courseDTOValidator.courseDtoValidator(courseDTO);
		ReferenceArtifactsDTOValidator referenceArtifactsDTOValidator = new ReferenceArtifactsDTOValidator();
		referenceArtifactsDTOValidator.referenceArtifactsDTOListValidator(courseDTO.getReferenceArtifacts());
		ReferenceUrlDTOValidator referenceUrlDTOValidator = new ReferenceUrlDTOValidator();
		referenceUrlDTOValidator.referenceUrlDTOListValidator(courseDTO.getRefernceUrl());

		// File fs=new File("C:/Users/Revature1/Downloads/jaf-1_1_1.zip");
		File fs = new File("C:/Users/Revature1/Downloads/rev-logo-2.png");
	/*	try {
			FileInputStream fis = new FileInputStream(fs);
		} catch (FileNotFoundException e) {
			throw new ServiceException("file not found", e);
		}*/
		String fileName = fs.getName();
		courseDTO.setIcon(fs);
		courseDTO.setIconName(fileName);
		boolean status = courseRepository.checkName(courseDTO.getName());
		if (status) {
			throw new ServiceException("Name already exists.");
		}
		status = true;
		int result = 0;
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		course = modelMapper.map(courseDTO, Course.class);
		result = courseRepository.addCourse(course);
		if (result == 0) {
			status = false;
		}
		return status;
	}

	public List<CourseDTO> viewCourses(String orderBy, String sortBy, int maxRows, int startIndex)
			throws ServiceException {
		List<CourseDTO> courseDTO;
		List<Course> courseList = courseRepository.viewCourses(orderBy, sortBy, maxRows, startIndex);
		if (!courseList.isEmpty()) {
			/*
			Stream<CourseDTO> courseListDto=courseList.stream();		
			courseListDto.forEach(p-> {
				List<ReferenceArtifactsDTO> referenceArtifactsDTOList = referenceArtifactsServices
							.viewReferenceArtifactsById(p.getId());
				p.setreferenceArtifacts(referenceArtifactsDTOList);
				List<ReferenceUrlDTO> referenceUrlDTOList = referenceUrlServices
						.viewReferenceUrlById(p.getId());
				p.setrefernceUrl(referenceUrlDTOList);
			});
			*/
			
			Type listType = new TypeToken<List<CourseDTO>>() {}.getType();
			modelMapper.getConfiguration().setAmbiguityIgnored(true); 
			courseDTO = modelMapper.map(courseList, listType);
			for (CourseDTO courseListDto : courseDTO) {
				List<ReferenceArtifactsDTO> referenceArtifactsDTOList = referenceArtifactsServices
						.viewReferenceArtifactsById(courseListDto.getId());
				courseListDto.setReferenceArtifacts(referenceArtifactsDTOList);

				List<ReferenceUrlDTO> referenceUrlDTOList = referenceUrlServices
						.viewReferenceUrlById(courseListDto.getId());
				courseListDto.setRefernceUrl(referenceUrlDTOList);
			}
		} else {
			throw new ServiceException("No records available.");
		}
		return courseDTO;
	}

	public CourseDTO viewCourseById(int id) throws ServiceException {
		Course course = null;
		CourseDTO courseDTO=new CourseDTO();
		course = courseRepository.viewCourseById(id);	
		if (course != null) {
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			courseDTO = modelMapper.map(course, CourseDTO.class);
			List<ReferenceArtifactsDTO> referenceArtifactsList = referenceArtifactsServices
					.viewReferenceArtifactsById(course.getId());
			courseDTO.setReferenceArtifacts(referenceArtifactsList);
			List<ReferenceUrlDTO> referenceUrlList = referenceUrlServices.viewReferenceUrlById(courseDTO.getId());
			courseDTO.setRefernceUrl(referenceUrlList);
		} else {
			throw new ServiceException("course not available");
		}
		return courseDTO; 
	}

	public boolean updateCourse(CourseDTO courseDTO) throws ServiceException, ValidatorException {
		boolean status = true;
		CourseDTOValidator courseDTOValidator = new CourseDTOValidator();
		courseDTOValidator.courseDtoValidator(courseDTO);
		ReferenceArtifactsDTOValidator referenceArtifactsDTOValidator = new ReferenceArtifactsDTOValidator();
		referenceArtifactsDTOValidator.referenceArtifactsDTOListValidator(courseDTO.getReferenceArtifacts());
		ReferenceUrlDTOValidator referenceUrlDTOValidator = new ReferenceUrlDTOValidator();
		referenceUrlDTOValidator.referenceUrlDTOListValidator(courseDTO.getRefernceUrl());
		if (viewCourseById(courseDTO.getId()).getId() == courseDTO.getId()) {
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			course = modelMapper.map(courseDTO, Course.class);
			int result = courseRepository.updateCourse(course);
			if (result == 0) {
				status = false;
				throw new ServiceException("unable to update course");
			}
			return status;
		} else {
			throw new ServiceException("details not available.");
		}
	}

	public boolean deleteCourseById(int id) throws ServiceException {
		if (viewCourseById(id).getId() == id) {
		return courseRepository.deleteCourseById(id);
	}
		else {
			throw new ServiceException("course not available.");
		}
		}
}
