package com.zsx.e_aop.c_beanproxy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.e_aop.a_jdkproxy.DeskService;

public class ECtest {
	@Test
	public void fun(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		DeskService deskService = ac.getBean("proxy", DeskService.class);
		deskService.addDesk();
		deskService.updateDesk();
		deskService.deleteDesk();
	}
	
}
