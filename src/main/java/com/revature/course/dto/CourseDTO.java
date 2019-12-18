package com.revature.course.dto;

import java.io.File;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CourseDTO {
	private int id;
	
	@NotNull
	@Size(min = 6, max = 50)
	private String name;
	@NotNull
	private LevelDTO level;
	@NotNull
	private CategoryDTO category;
	@NotNull
	@Size(min = 2, max = 50)
	private String tags;
	@Size(min = 0, max = 50)
	private String slug;
	
	private boolean levelOverRide;
	@NotNull
	private int enrollmentPoint;
	@NotNull
	private int completionPoint;
	private boolean preSignUp;
	private boolean loggedInViaSlug;
	@Size(min = 0, max = 50)
	private String description;	
	@Size(min = 0, max = 50)
	private String metaKeyword;
	@Size(min = 0, max = 50)
	private String metaDescription;		
	private List<ReferenceArtifactsDTO> referenceArtifacts;
	private List<ReferenceUrlDTO> refernceUrl;
	private UsersDTO createdBy;
	private File icon;
	@NotNull
	@Size(min = 0, max = 50)
	private String iconName;
	private boolean status; 
	private int version;
}
