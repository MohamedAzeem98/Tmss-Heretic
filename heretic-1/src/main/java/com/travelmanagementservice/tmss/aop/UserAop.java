package com.travelmanagementservice.tmss.aop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAop {
	
	 private static final Logger logger = LoggerFactory.getLogger(UserAop.class);

	
	@Before("execution(*com.travelmanagementservice.tmss.service.UserServiceImpl.*(..))")
	public void beforeServiceMethod(JoinPoint joinPoint) {
		logger.info("[AOP] About to execute method: "+joinPoint.getSignature().getName());
	}
	
	
	@After("execution(*com.travelmanagementservice.tmss.service.UserServiceImpl.*(..))")
	public void afterServiceMethod(JoinPoint joinPoint) {
		logger.info("[AOP] been executed successfully : "+joinPoint.getSignature().getName());
	}

	
	@Before("execution(*com.travelmanagementservice.tmss.service.TravelRequisitionServiceImpl.*(..))")
	public void beforeTravelServiceMethod(JoinPoint joinpoint){
		logger.info("[AOP] About to execute method: "+joinpoint.getSignature().getName());
	}
	
	
	@After("execution(*com.travelmanagementservice.tmss.service.TravelRequisitionServiceImpl.*(..))")
	public void afterTravelServiceMethod(JoinPoint joinpoint) {
		logger.info("[AOP] been executed successfully : "+joinpoint.getSignature().getName());
	}
}
