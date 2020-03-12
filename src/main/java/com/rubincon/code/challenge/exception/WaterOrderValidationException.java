package com.rubincon.code.challenge.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WaterOrderValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public WaterOrderValidationException(String message) {
		super(message);
	} 
}
