package com.zsx.d_attributein.b_setter;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBtest {
	@Test
	public void fun1(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		Person person = ac.getBean("person", Person.class);
		System.out.println(person);
	}
	
}
