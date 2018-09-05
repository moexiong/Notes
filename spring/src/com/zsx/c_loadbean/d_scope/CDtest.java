package com.zsx.c_loadbean.d_scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.a_ioc.UserService;

public class CDtest {
	//bean中scope配置的为默认值singleton时   值：true
	//bean中scope配置的为多例prototype时	     值：false
	@Test
	public void fun1(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		UserService us = ac.getBean("userServiceId", UserService.class);
		UserService us2 = ac.getBean("userServiceId", UserService.class);
		
		System.out.println(us==us2);
	}
	
}
