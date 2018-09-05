package com.zsx.f_jdbctemplate.c_dbcp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.f_jdbctemplate.a_domain.Paper;

public class FCtest {
	
	@Test
	public void fun(){
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		PaperDaoImpl paperDao = ac.getBean("paperDao", PaperDaoImpl.class);
		for (Paper paper : paperDao.findAllPaper()) {
			System.out.println(paper);
		}
	}
	
}
