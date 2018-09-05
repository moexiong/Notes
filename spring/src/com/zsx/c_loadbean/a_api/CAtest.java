package com.zsx.c_loadbean.a_api;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.zsx.a_ioc.UserService;

public class CAtest {
	
	@Test
	public void fun1(){
		//常用
		//使用ApplicationContext会立即加载bean
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		UserService userService = ac.getBean("userServiceId", UserService.class);
		userService.addUser();
	}
	
	@Test
	public void fun2(){
		//使用BeanFactory会延迟加载bean
		String xmlPath = "applicationContext.xml";
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource(xmlPath));
		UserService userService = bf.getBean("userServiceId", UserService.class);
		userService.addUser();
	}
}
