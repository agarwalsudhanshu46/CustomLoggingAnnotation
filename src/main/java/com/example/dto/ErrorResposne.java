package com.example.dto;

import lombok.Data;

@Data
public class ErrorResposne {
	String message;
	int httpStatusCode;

}
