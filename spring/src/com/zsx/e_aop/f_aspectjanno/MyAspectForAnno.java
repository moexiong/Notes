package com.zsx.e_aop.f_aspectjanno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//aspectJ--> 基于注解
//配置哪一个就用哪一个，一般只有around就足够了
@Component		//声明为一般组件
@Aspect			//声明为一般组件中的切面
public class MyAspectForAnno {
	//@Pointcut声明切入点表达式
	//private void myExecution(){};声明切入点表达式的引用myExecution，即id=方法名
	@Pointcut("execution(* com.zsx.e_aop.e_aspectjxml.PenServiceImpl.*(..))")
	private void myExecution(){};
	
	//@Before(value = "myExecution()")
	public void myBefore(JoinPoint joinPoint){
		System.out.println("（注解）前置通知\t" + joinPoint.getSignature().getName());
	}
	
	//@AfterReturning(value = "myExecution()", returning = "ret")
	public Object myAfterReturning(JoinPoint joinPoint, Object ret){
		System.out.println("（注解）后置通知\t" + ret);
		return ret;
	}
	
	//@Around(value = "myExecution()")
	public Object myAround(ProceedingJoinPoint proceed) throws Throwable{
		System.out.println("（注解）前环绕通知\t");
		Object object = proceed.proceed();
		System.out.println("（注解）后环绕通知\t");
		return object;
	}
	
	//@AfterThrowing(value = "myExecution()", throwing = "e")
	public void myAfterThrowing(Throwable e){
		System.out.println("（注解）异常通知\t" + e.getMessage());
	}
	
	@After("myExecution()")
	public void myAfter(JoinPoint joinPoint){
		System.out.println("（注解）最终通知\t");
	}
}
