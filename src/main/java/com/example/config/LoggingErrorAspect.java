package com.example.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LoggingErrorAspect {
	private static Logger LOGGER = LoggerFactory.getLogger(LoggingErrorAspect.class);

	@AfterThrowing(pointcut = "@annotation(LoggingEvent)", throwing = "exception")
	public void logExecutionTime(JoinPoint joinPoint, RuntimeException exception) throws Throwable {
		LOGGER.info("the item id is invalid");
		LOGGER.info("this is logging by using spring error aop, @AfterThrowing advice");

	}
}
