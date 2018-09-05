package com.zsx.c_loadbean.c_factory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.a_ioc.UserService;

public class CCtest {
	@Test
	public void fun1(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		UserService us = ac.getBean("userService", UserService.class);
		us.addUser();
	}
	
}
