package com.example.dto;

import lombok.Data;

/**
 * This class is acting as a DTO and contains the errorResponse.
 * 
 * @author sagarwal
 *
 */
@Data
public class ErrorResposne {
	String message;
	int httpStatusCode;

}
