package com.revature.course.junitTests.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.course.dto.CategoryDTO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.LevelDTO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.dto.UsersDTO;

public class CourseDTOUtil {
	public static CourseDTO getCourseDTODetails() {
		CourseDTO course = new CourseDTO();
		course.setId(1);
		course.setName("java");
		LevelDTO level = new LevelDTO();
		level.setId(1);
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(1);
		course.setCategory(categoryDTO);
		course.setLevel(level);
		course.setCompletionPoint(100);
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setId(1);
		usersDTO.setEmail("chandra@gmail.com");
		usersDTO.setName("chandra");
		course.setCreatedBy(usersDTO);
		course.setDescription("it is description");
		course.setEnrollmentPoint(100);
		course.setLevelOverRide(true);
		course.setLoggedInViaSlug(true);
		course.setPreSignUp(true);
		course.setMetaDescription("it is meta description");
		course.setMetaKeyword("it is meta keyword");
		course.setSlug("www.app.revature.com/courses/java");
		course.setStatus(true);
		course.setTags("java,java programming");
		List<ReferenceArtifactsDTO> referenceArtifactsDTOList = new ArrayList<>();
		ReferenceArtifactsDTO referenceArtifactsDTO = new ReferenceArtifactsDTO();
		referenceArtifactsDTO.setDescription("description for interfaces");
		referenceArtifactsDTO.setName("interfaces");
		referenceArtifactsDTO.setType("Main content");
		referenceArtifactsDTOList.add(referenceArtifactsDTO);
		course.setReferenceArtifactsId(referenceArtifactsDTOList);
		referenceArtifactsDTO = new ReferenceArtifactsDTO();
		referenceArtifactsDTO.setDescription("description for polymorphism");
		referenceArtifactsDTO.setName("polymorphism");
		referenceArtifactsDTO.setType("Main content");
		referenceArtifactsDTOList.add(referenceArtifactsDTO);
		course.setReferenceArtifactsId(referenceArtifactsDTOList);
		List<ReferenceUrlDTO> ReferenceUrlDTOList = new ArrayList<>();
		ReferenceUrlDTO referenceUrlDTO = new ReferenceUrlDTO();
		referenceUrlDTO.setDescription("description for interfaces");
		referenceUrlDTO.setName("interfaces");
		referenceUrlDTO.setType("Main content");
		referenceUrlDTO.setTutorial(false);
		referenceUrlDTO.setUrl("www.revature.com/interfaces");
		ReferenceUrlDTOList.add(referenceUrlDTO);
		course.setRefernceUrlId(ReferenceUrlDTOList);
		referenceUrlDTO = new ReferenceUrlDTO();
		referenceUrlDTO.setDescription("description for polymorphism");
		referenceUrlDTO.setName("polymorphism");
		referenceUrlDTO.setType("Main content");
		referenceUrlDTO.setTutorial(true);
		referenceUrlDTO.setUrl("www.revature.com/polymorphism");
		ReferenceUrlDTOList.add(referenceUrlDTO);
		course.setRefernceUrlId(ReferenceUrlDTOList);
		course.setIconName("C:/Users/Revature1/Downloads/rev-logo-2.png");
		return course;
	}
}
