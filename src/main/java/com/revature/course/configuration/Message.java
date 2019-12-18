package com.revature.course.configuration;

import lombok.Data;

@Data
public class Message {
	private String infoMessage;
	private String errorMessage;
	private int status=200;

	public Message(String error) {
		super();
		this.errorMessage = error;
	}

	public Message() {
	}
}
