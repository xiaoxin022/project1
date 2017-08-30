package com.maven.springtest01.service.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAdvice{
	
	@Before("execution(* com.maven.springtest01.service.UserService.hasMatchUser(..))")
	public void before(JoinPoint jp){
		System.out.println(jp.getTarget().getClass());
		System.out.println("Log..start");
	}
	@After("execution(* com.maven.springtest01.service.UserService.*(..))")
	public void after(){
		System.out.println("Log..end");
	}
}