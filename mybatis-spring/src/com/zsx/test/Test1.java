package com.zsx.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsx.mapper.UserMapper;
import com.zsx.pojo.User;

public class Test1 {
	
	@Test
	public void fun1() throws Exception {
		String xmlPath = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
		UserMapper bean = ac.getBean(UserMapper.class);
		List<User> users = bean.findAllUsers();
		System.out.println(users);
	}
	
}
