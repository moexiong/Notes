package com.zsx.e_aop.b_cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.zsx.e_aop.a_jdkproxy.DeskService;
import com.zsx.e_aop.a_jdkproxy.DeskServiceImpl;
import com.zsx.e_aop.a_jdkproxy.MyAdvice;

public class MyBeanFactory {
	
	public static DeskService getService(){
		//1.需要切入点实例（目标类）
		final DeskService deskService = new DeskServiceImpl();
		//2.需要通知实例（切面类）
		final MyAdvice advice = new MyAdvice();
		//3.生成代理对象 使用CGlib
		//3.1核心类
		Enhancer enhancer = new Enhancer();
		//3.2确定父类
		enhancer.setSuperclass(deskService.getClass());
		//3.3设置回调函数 参数：MethodInterceptor 等效于 jdk 的 invoke
		enhancer.setCallback(new MethodInterceptor() {
			//参数1.2.3同jdk，参数4是方法的代理
			@Override
			public Object intercept(Object proxy, Method method, Object[] args,
					MethodProxy methodProxy) throws Throwable {
				Object obj = null;
				if ("deleteDesk".equals(method.getName())) {
					obj = method.invoke(deskService, args);
				}else {
					advice.adBefore();
					//参数1.目标对象 2.传入的参数（一般不动它）
					method.getName();
					obj = method.invoke(deskService, args);
					//methodProxy.invokeSuper(proxy, args);与上一句的效果相同
					advice.adAfter();
					//需要将返回值返回
				}
				return obj;
			}
		});
		//3.4生成代理对象
		DeskService proxy = (DeskService) enhancer.create();
		return proxy;
	}
	
}
