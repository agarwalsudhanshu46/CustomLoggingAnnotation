package com.example.exception;

import org.springframework.http.HttpStatus;

public interface CustomException {
	HttpStatus getHttpResponseCode();

}
