package com.revature.course.dto;

import java.io.File;
import java.util.List;
import lombok.Data;

@Data
public class CourseDTO {
	private int id;
	private String name;
	private LevelDTO level;
	private CategoryDTO category;
	private String tags;
	private String slug;
	private boolean levelOverRide;
	private int enrollmentPoint;
	private int completionPoint;
	private boolean preSignUp;
	private boolean loggedInViaSlug;
	private String description;	
	private String metaKeyword;	
	private String metaDescription;		
	private List<ReferenceArtifactsDTO> referenceArtifactsId;
	private List<ReferenceUrlDTO> refernceUrlId;
	private UsersDTO createdBy;
	private File icon;
	private String iconName;
	private boolean status; 
	private int version;
}
