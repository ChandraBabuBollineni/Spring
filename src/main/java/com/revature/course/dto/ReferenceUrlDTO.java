package com.revature.course.dto;

import lombok.Data;

@Data
public class ReferenceUrlDTO {
	private int id;
	private String type;
	private int courseId;
	private String name;
	private String url;	
	private boolean tutorial;
	private String description;
}
