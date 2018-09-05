package com.zsx.e_aop.d_aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.e_aop.a_jdkproxy.DeskService;

public class EDtest {
	@Test
	public void fun(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		DeskService deskService = ac.getBean("deskService", DeskService.class);
		deskService.addDesk();
		deskService.updateDesk();
		deskService.deleteDesk();
	}
	
}
