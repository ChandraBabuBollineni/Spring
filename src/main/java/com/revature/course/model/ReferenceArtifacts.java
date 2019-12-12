package com.revature.course.model;


import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ReferenceArtifacts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "course_type")
	private String type;
	
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "file")
	private File file;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "description")
	private String description;
}

