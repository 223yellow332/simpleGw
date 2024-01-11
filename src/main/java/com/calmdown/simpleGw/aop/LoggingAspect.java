package com.calmdown.simpleGw.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect     // AOP 사용
@Component  // Bean 으로 등록 
public class LoggingAspect {
	
	/* controller 패키지에 포함된 public 메서드와 매칭 */
	@Pointcut("within(com.calmdown.simpleGw.controller..*)")
	public void onRequest() { }
	
	@Before("onRequest()")
	public void beforeRequest(JoinPoint joinPoint) {
		//MDC Tracing id Setting
		MDC.put(LogConstant.TRACING_ID, UUID.randomUUID().toString());

	    log.info("###Start request {}", joinPoint.getSignature().toShortString());
	    Arrays.stream(joinPoint.getArgs())
	            .map(Object::toString)
	            .map(str -> "\t" + str)
	            .forEach(log::info);
	}
	
	@AfterReturning(pointcut = "onRequest()", returning = "returnValue")
	public void afterReturningLogging(JoinPoint joinPoint, Object returnValue) {
	    log.info("###End request {}", joinPoint.getSignature().toShortString());

	    if (returnValue == null) return;

	    log.info("\t{}", returnValue.toString());

		// MDC Clear
		MDC.clear();
	}
	
	@AfterThrowing(pointcut = "onRequest()", throwing = "e")
	public void afterThrowingLogging(JoinPoint joinPoint, Exception e) {
	    log.error("###Occured error in request {}", joinPoint.getSignature().toShortString());
	    log.error("\t{}", e.getMessage());
	}
	
}
