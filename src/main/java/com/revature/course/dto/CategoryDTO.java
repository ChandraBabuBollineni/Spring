package com.revature.course.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoryDTO {
	@NotNull
	private int id;
	
}
