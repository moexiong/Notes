package com.zsx.e_aop.a_jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {
	
	public static DeskService getService(){
		//1.需要切入点实例（目标类）
		final DeskService deskService = new DeskServiceImpl();
		//2.需要通知实例（切面类）
		final MyAdvice advice = new MyAdvice();
		//3.生成代理对象 参数1.类加载器（一般使用当前类，也可使用目标类）2.代理类需要实现的接口（与目标对象需要实现的接口一致）3.匿名内部类，实现反射（需要传入的实例必须为final类型）
		DeskService proxy = (DeskService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(), deskService.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object obj = null;
				if ("deleteDesk".equals(method.getName())) {
					obj = method.invoke(deskService, args);
				}else {
					advice.adBefore();
					//参数1.目标对象 2.传入的参数（一般不动它）
					method.getName();
					obj = method.invoke(deskService, args);
					advice.adAfter();
					//需要将返回值返回
				}
				return obj;
			}
		});
		return proxy;
	}
	
}
