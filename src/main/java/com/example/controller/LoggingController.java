package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aspect.LoggingEvent;
import com.example.exception.CustomApplicationException;

/**
 * 
 * This class is acting as a controller layer which contains the various
 * end-points to log the information.
 * 
 * @author sagarwal
 *
 */
@RestController
@RequestMapping(path = "/api/v1/logging")
public class LoggingController {
	private static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@GetMapping(path = "/itemData/{itemId}")
	@LoggingEvent
	public String loggingItemData(@PathVariable("itemId") String itemId) {
		if (StringUtils.isEmpty(itemId) || itemId.length() < 2) {
			throw new CustomApplicationException("invalid itemId value");
		} else {
			LOGGER.info("I am from controller");
		}
		return "10";

	}

	@GetMapping(path = "/nameData")
	public String loggingName(@RequestParam("name") String name) {
		if (StringUtils.isEmpty(name) || name.length() <= 5) {
			throw new CustomApplicationException("invalid name");
		} else {
			LOGGER.info("I am from controller");
		}
		return "sid";

	}

	@GetMapping(path = "/phoneData")
	public String loggingPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
		if (StringUtils.isEmpty(phoneNumber) || phoneNumber.length() != 10) {
			throw new CustomApplicationException("invalid phoneNumber");
		} else {
			LOGGER.info("I am from controller");
		}
		return "9090912341";

	}

}
