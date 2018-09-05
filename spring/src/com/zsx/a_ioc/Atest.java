package com.zsx.a_ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Atest {
	
	@Test
	public void fun1(){
		//old
		UserService userService = new UserServiceImpl();
		userService.addUser();
	}
	
	@Test
	public void fun2(){
		//new
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		UserService us = (UserService) ac.getBean("userServiceId");//userServiceId就是applicationContext.xml里面配置的引用id
		us.addUser();
	}
}
