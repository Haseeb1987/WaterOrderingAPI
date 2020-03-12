package com.rubincon.code.challenge.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	@Before("allMethods()")
	public void logAdvice(JoinPoint joinPoint){
		System.out.println("WaterOrderController method : " + joinPoint.getSignature() + " is called ...");
	}
	
	@Pointcut ("execution(* com.rubincon.code.challenge.controller.WaterOrderController.*(..))")
	public void allMethods(){}
}
