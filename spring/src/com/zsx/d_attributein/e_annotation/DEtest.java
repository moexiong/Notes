package com.zsx.d_attributein.e_annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DEtest {
	@Test
	public void fun1(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		StudentAction action = ac.getBean("studentAction", StudentAction.class);
		action.execute();
	}
	
}
