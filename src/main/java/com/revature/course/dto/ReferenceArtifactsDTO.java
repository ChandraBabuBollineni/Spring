package com.revature.course.dto;


import lombok.Data;

@Data
public class ReferenceArtifactsDTO {
	private int id;
	private String name;
	private String type;
	private int courseId;
	private String fileName;
	private String description;
}
