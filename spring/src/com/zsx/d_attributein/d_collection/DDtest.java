package com.zsx.d_attributein.d_collection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DDtest {
	@Test
	public void fun1(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		CollData collData = ac.getBean("collData", CollData.class);
		System.out.println(collData);
	}
	
}
