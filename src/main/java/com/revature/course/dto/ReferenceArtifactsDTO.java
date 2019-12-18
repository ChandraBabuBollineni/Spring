package com.revature.course.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ReferenceArtifactsDTO {
	private int id;
	@NotNull
	@Size(min = 5, max = 50)
	private String name;
	@NotNull
	@Size(min = 5, max = 50)
	private String type;
	private int courseId;
	@NotNull
	@Size(min = 5, max = 50)
	private String fileName;
	@Size(min = 0, max = 50)
	private String description;
}
