package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.LoggingEvent;
import com.example.exception.CustomApplicationException;

@RestController
@RequestMapping(path = "/logging")
public class LoggingController {
	private static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@GetMapping(path = "/data/{itemId}")
	@LoggingEvent
	public String printData(@PathVariable("itemId") String itemId) {
		if (StringUtils.isEmpty(itemId) || itemId.length() < 2) {
			throw new CustomApplicationException("invalid itemId value");
		} else {
			LOGGER.info("I am from controller");
		}
		return "sid";

	}

}
