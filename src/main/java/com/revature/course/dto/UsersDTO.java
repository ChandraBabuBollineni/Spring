package com.revature.course.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsersDTO {
@NotNull
private int id;
@NotNull
@Size(min = 5, max = 50)
private String name;
@NotNull
@Email
private String email;
}
