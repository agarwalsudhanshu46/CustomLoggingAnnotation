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

	@AfterThrowing(pointcut = "@annotation(LoggingEvent) || execution(String loggingNa*(..))", throwing = "exception")
	public void logAfterThrowingException(JoinPoint joinPoint, RuntimeException exception) throws Throwable {
		
		LOGGER.info("this is logging by using spring error aop, @AfterThrowing advice");

	}
	
	/*Note:- loggingNameMethodAfterAdvice and logAfterThrowingException are belongs to  
	same method as loggingNameMethod is defined in the com.example.controller.* package
	So both loggingNameMethodAfterAdvice of LoggingAspect class and 
	logAfterThrowingException method of LoggingErrorAspect class will run.
	But first logAfterThrowingException method will be execute in case exception will  be thrown
	and then loggingNameMethodAfterAdvice method will be executed, 
	otherwise loggingNameMethodAfterAdvice method will be executed*/
}
