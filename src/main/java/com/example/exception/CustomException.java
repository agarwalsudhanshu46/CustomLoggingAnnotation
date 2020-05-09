package com.example.exception;

import org.springframework.http.HttpStatus;

/**
 * This class is acting as a custom exception, and implemented by all the
 * applicaion custom exception.
 * 
 * @author sagarwal
 *
 */
public interface CustomException {
	HttpStatus getHttpResponseCode();

}
