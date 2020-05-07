package com.example.exception;

import org.springframework.http.HttpStatus;

public class CustomApplicationException extends RuntimeException implements CustomException {


	private static final long serialVersionUID = 1L;

	public CustomApplicationException(String message) {
        super(message);
    }

	public CustomApplicationException() {
		super();
		
	}

	public CustomApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CustomApplicationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CustomApplicationException(Throwable cause) {
		super(cause);
		
	}

	@Override
	public HttpStatus getHttpResponseCode() {
		return HttpStatus.CONFLICT;
	}
    
}
