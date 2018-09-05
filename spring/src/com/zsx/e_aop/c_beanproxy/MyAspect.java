package com.zsx.e_aop.c_beanproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
//MethodInterceptor在方法前后执行的接口
//切面类
public class MyAspect implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object obj = null;
		if ("deleteDesk".equals(mi.getMethod().getName())) {
			obj = mi.proceed();
		}else {
			MyAspect.adBefore();
			
			obj = mi.proceed();
			
			MyAspect.adAfter();
		}
		return obj;
	}
	
	public static void adBefore(){
		System.out.println("adBefore执行了。。。（通知）");
	}
	
	public static void adAfter(){
		System.out.println("adAfter执行了。。。（通知）");
	}
	
}
