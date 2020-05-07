package com.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.dto.ErrorResposne;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

	@ExceptionHandler(CustomApplicationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public final ErrorResposne handleCustomException(CustomApplicationException exception) {
		ErrorResposne errorResposne = new ErrorResposne();
		errorResposne.setHttpStatusCode(exception.getHttpResponseCode().value());
		errorResposne.setMessage(exception.getMessage());
		LOGGER.error(errorResposne.toString());
		return errorResposne;

	}
}
