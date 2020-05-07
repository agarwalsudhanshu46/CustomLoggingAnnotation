package com.example.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-13)
public class LoggingAspect {
	private static Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@AfterReturning(pointcut = "@annotation(LoggingEvent)", returning = "returnValue")
	public void logExecutionTime(JoinPoint joinPoint, Object returnValue) throws Throwable {

		Object[] args = joinPoint.getArgs();
		if (args.length >= 0 && args[0] instanceof String) {
			String itemId = (String) args[0];
			if (itemId.length() >= 2) {
				LOGGER.info("the item id is valid and value is " + args[0]);
			}
		}
		LOGGER.info("this is logging by using spring aop, @AfterReturning advice");

	}

}
