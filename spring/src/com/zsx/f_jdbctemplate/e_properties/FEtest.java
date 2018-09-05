package com.zsx.f_jdbctemplate.e_properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.f_jdbctemplate.a_domain.Paper;
import com.zsx.f_jdbctemplate.d_c3p0jdbcsup.PaperDaoImpl;

public class FEtest {
	
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
