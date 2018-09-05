package com.zsx.crud;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zsx.dao_old.UserDao;
import com.zsx.dao_old.UserDaoImpl;
import com.zsx.pojo.User;

public class TestDaoOld {
	
	private SqlSessionFactory sessionFactory;
	@Before
	public void before() throws Exception {
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		sessionFactory = new SqlSessionFactoryBuilder().build(in);
	}
	@Test
	public void fun1(){
		UserDao userDao = new UserDaoImpl(sessionFactory);
		User user = userDao.findUserById(28);
		System.out.println(user);
	}
	
}
