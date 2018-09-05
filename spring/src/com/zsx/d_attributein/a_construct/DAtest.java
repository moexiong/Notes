package com.zsx.d_attributein.a_construct;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DAtest {
	@Test
	public void fun1(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		User user = ac.getBean("user", User.class);
		System.out.println(user);
	}
	
}
