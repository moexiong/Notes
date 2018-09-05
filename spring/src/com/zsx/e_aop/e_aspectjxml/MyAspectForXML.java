package com.zsx.e_aop.e_aspectjxml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//aspectJ--> 基于xml
//配置哪一个就用哪一个，一般只有around就足够了
public class MyAspectForXML {
	//（可写可不写）JoinPoint 每个方法的第一个参数都有，☞的是切入点，他是aspectJ下的接口
	//前置通知
	public void myBefore(JoinPoint joinPoint){
		System.out.println("（xml）前置通知\t" + joinPoint.getSignature().getName());
	}
	//（必须）Object ret是后置通知接收的目标方法返回值，变量名由配置文件中指定
	//后置通知
	public Object myAfterReturning(JoinPoint joinPoint, Object ret){
		System.out.println("（xml）后置通知\t" + ret);
		return ret;
	}
	//（必须）ProceedingJoinPoint proceed是环绕通知能够执行目标方法的必须参数
	//环绕通知
	public Object myAround(ProceedingJoinPoint proceed) throws Throwable{
		System.out.println("（xml）前环绕通知\t");
		Object object = proceed.proceed();
		System.out.println("（xml）后环绕通知\t");
		return object;
	}
	//（必须）Throwable e是异常通知的异常消息，变量名由配置文件中指定
	//异常通知
	public void myAfterThrowing(Throwable e){
		System.out.println("（xml）异常通知\t" + e.getMessage());
	}
	//最终通知
	public void myAfter(JoinPoint joinPoint){
		System.out.println("（xml）最终通知\t");
	}
}
