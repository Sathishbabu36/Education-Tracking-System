package com.project.ets.exception;

import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
public class RatingNotFoundByIdException extends RuntimeException{

	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}
}
