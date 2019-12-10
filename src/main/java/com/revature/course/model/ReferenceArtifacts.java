package com.revature.course.model;


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
	@Column(name = "type")
	private String type;
	
	@Column(name = "file")
	private byte[] file;
	
	@Column(name = "description")
	private String description;
}
