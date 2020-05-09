package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * This class is acting as a aspect and contains the various advice.
 * 
 * @author sagarwal
 *
 */
@Aspect
@Component
@Order(-13)
public class LoggingAspect {
	private static Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	/*
	 * using @annotation, we have use the custom annotation LoggingEvent, as value
	 * of pointcut in @AfterReturning advice, we can use the return value of
	 * LoggingEvent annotation method inside this method
	 */
	@AfterReturning(pointcut = "@annotation(LoggingEvent)", returning = "returnValue")
	public void logAfterReturning(JoinPoint joinPoint, Object returnValue) throws Throwable {

		Object[] args = joinPoint.getArgs();
		if (args.length >= 0 && args[0] instanceof String) {
			String itemId = (String) args[0];
			if (itemId.length() >= 2) {
				LOGGER.info("the item id is valid and value is " + args[0]);
			}
		}
		LOGGER.info("this is logging by using spring aop, @AfterReturning advice");
		LOGGER.info("this is logging by using spring aop and return value of LoggingEvent annotated method is {}",
				returnValue);

	}

	// declaring pointcuts, it will apply all methods starts with LoggingNa and
	// having return type as String.
	@Pointcut(value = "execution(String loggingNa*(..))")
	public void loggingNameMethod() {
	}

	// if this pointcut used in any advice it means it applies all the class methods
	// of controller package(com.example.controller.*).
	@Pointcut(value = "within(com.example.controller.*)")
	public void inControllerPackage() {
	}

	/*
	 * Note:- loggingNameMethodBeforeAdvice and loggingNameMethodInControllerPackage
	 * are belongs to same method as loggingNameMethod is defined in the
	 * com.example.controller.* package So both loggingNameMethodBeforeAdvice and
	 * loggingNameMethodInControllerPackage method will run. But first
	 * loggingNameMethodBeforeAdvice method will be execute as we have added
	 * directly method level pointcut here and then
	 * loggingNameMethodInControllerPackage method will be executed.
	 */
	// combine two pointcuts and applied on before advice
	@Before(value = "loggingNameMethod() && inControllerPackage()")
	public void loggingNameMethodInControllerPackage(JoinPoint joinPoint) {

		LOGGER.info("this is logging by using spring aop for loggingName method of controller class, @Before advice");
	}

	// use one pointcut on before advice
	@Before(value = "loggingNameMethod()")
	public void loggingNameMethodBeforeAdvice(JoinPoint joinPoint) {

		LOGGER.info("this is logging by using spring aop for loggingName method, @Before advice");
	}

	/*
	 * Note: loggingNameMethodAfterAdvice and logAfterThrowingException are belongs
	 * to same method as loggingNameMethod is defined in the
	 * com.example.controller.* package So both loggingNameMethodAfterAdvice of
	 * LoggingAspect class and logAfterThrowingException method of
	 * LoggingErrorAspect class will run. But first logAfterThrowingException method
	 * will be execute in case exception will be thrown and then
	 * loggingNameMethodAfterAdvice method will be executed, otherwise, in case of
	 * no exception, loggingNameMethodAfterAdvice method only will be executed
	 */

	// use one pointcut on after advice, if exception is thrown or not from
	// loggingNameMethod, in both cases the loggingNameMethodAfterAdvice will be
	// executed.
	@After(value = "loggingNameMethod()")
	public void loggingNameMethodAfterAdvice(JoinPoint joinPoint) {

		LOGGER.info("this is logging by using spring aop for loggingName method, @After advice");
	}

	/*
	 * use one pointcut on around advice, with method name loggingPhoneNumber Method
	 * to execute some statements before method execution and after method
	 * execution.
	 * 
	 * but, in case exception will be thrown from method loggingPhoneNumber method
	 * then in the method loggingPhoneNumberMethodAroundAdvice, only the logger will
	 * be printed which is log before proceedJointPoint.proceed(obj).
	 */
	// with any type of return type
	@Around(value = "execution(* loggingPhoneNumber(..))")
	public void loggingPhoneNumberMethodAroundAdvice(ProceedingJoinPoint proceedJointPoint) throws Throwable {

		LOGGER.info(
				"this is logging by using spring aop, before loggingPhoneNumber method excution, by using  @Around advice");
		Object[] obj = proceedJointPoint.getArgs();

		Object phoneNumber = proceedJointPoint.proceed(obj);
		LOGGER.info(
				"this is logging by using spring aop, after loggingPhoneNumber method excution,  by using  @Around advice");
		LOGGER.info("this is logging by using spring aop and return value of LoggingEvent annotated method is {}",
				phoneNumber);

	}

}
