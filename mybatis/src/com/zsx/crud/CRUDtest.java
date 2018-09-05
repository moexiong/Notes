package com.zsx.crud;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zsx.pojo.User;

//这个所有使用的方法配置的都是基于user_old.xml文件
public class CRUDtest {
	
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
	//通过ID查询一个用户
	@Test
	public void fun1() throws IOException{
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		//4.使用session对象来进行相关CRUD操作
		
		User user = session.selectOne("user.findUserById", 10);
		System.out.println(user);
		
		//-----------------------------------
		session.close();
	}
	//通过名称模糊查询用户
	@Test
	public void fun2() throws IOException{
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		//4.使用session对象来进行相关CRUD操作
		User user = new User();
		user.setUsername("三");
		List<User> users = session.selectList("user.findUsersByName", user);
		for (User u : users) {
			System.out.println(u);
		}
		
		//-----------------------------------
		session.close();
	}
	//添加用户并返回所添加的用户的ID
	@Test
	public void fun3() throws IOException{
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		//4.使用session对象来进行相关CRUD操作
		User user = new User();
		user.setUsername("杰瑞");
		user.setSex("女");
		user.setBirthday(new Date());
		user.setAddress("米国");
		int i = session.insert("user.saveUser", user);
		System.out.println("插入成功的数据条数：" + i);
		System.out.println("插入的数据的ID：" + user.getId());
		//5.手动提交事务
		session.commit();
		//-----------------------------------
		session.close();
	}
	//通过ID更新数据
	@Test
	public void fun4() throws IOException{
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		//4.使用session对象来进行相关CRUD操作
		User user = new User();
		user.setId(29);
		user.setUsername("小杰瑞");
		user.setSex("男");
		user.setBirthday(new Date());
		user.setAddress("米国");
		int i = session.update("user.updateUserById", user);
		System.out.println("更新成功的数据条数：" + i);
		//5.手动提交事务
		session.commit();
		//-----------------------------------
		session.close();
	}
	//通过ID删除数据
	@Test
	public void fun5() throws IOException{
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		//4.使用session对象来进行相关CRUD操作
		User user = new User();
		user.setId(22);
		int i = session.delete("user.deleteUserById", user);
		System.out.println("删除成功的数据条数：" + i);
		//5.手动提交事务
		session.commit();
		//-----------------------------------
		session.close();
	}
}
