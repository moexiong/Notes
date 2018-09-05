package com.zsx.c_loadbean.e_lifecycle;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CEtest {
	//第一种关闭的方式
	@Test
	public void fun1(){
		String xmlPath = "applicationContext.xml";
		//接口ApplicationContext没有close方法，ClassPathXmlApplicationContext里面有close
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		LifeService ls = ac.getBean("lifeService", LifeService.class);
		ls.live();
		//需要关闭ac容器才能销毁实例，使用反射来调用close方法
		try {
			ac.getClass().getMethod("close").invoke(ac);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//第二种关闭的方式
	@Test
	public void fun2(){
		String xmlPath = "applicationContext.xml";
		//直接返回ClassPathXmlApplicationContext这个实现类的对象，他有close方法
		ClassPathXmlApplicationContext cpxaca = new ClassPathXmlApplicationContext(xmlPath);
		LifeService ls = cpxaca.getBean("lifeService", LifeService.class);
		ls.live();
		cpxaca.close();
	}
}
