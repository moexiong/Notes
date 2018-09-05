package com.zsx.e_aop.f_aspectjanno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.e_aop.e_aspectjxml.PenService;

public class EFtest {
	
	@Test
	public void fun(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		PenService penService = ac.getBean("penService", PenService.class);
		penService.addPen();
		penService.updatePen();
		penService.deletePen();
	}
	
}
