package com.zsx.c_loadbean.b_staticfactory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.a_ioc.UserService;

public class CBtest {
	@Test
	public void fun1(){
		//old
		UserService us = MyStaticFactory.getService();
		us.addUser();
	}
	@Test
	public void fun2(){
		//spring
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		UserService us = ac.getBean("myStaticFactory", UserService.class);
		us.addUser();
	}
}
