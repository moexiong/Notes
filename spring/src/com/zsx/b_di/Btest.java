package com.zsx.b_di;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Btest {
	
	@Test
	public void fun1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bs = (BookService) ac.getBean("bookService");
		bs.addBook();
	}
	
}
