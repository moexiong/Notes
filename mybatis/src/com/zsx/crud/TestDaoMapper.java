package com.zsx.crud;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.zsx.dao_mapper.OrderMapper;
import com.zsx.dao_mapper.UserMapper;
import com.zsx.pojo.Orders;
import com.zsx.pojo.QueryVo;
import com.zsx.pojo.User;

public class TestDaoMapper {
	
	@Test
	public void fun1() throws IOException{
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		
		//4.使用session对象获取到dao里的Mapper对象
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User user = userMapper.findUserById(28);
		System.out.println(user);
		
		//-----------------------------------
		session.close();
	}
	//封装User到QueryVo，通过user中的username来模糊查询user
	@Test
	public void fun2() throws IOException{
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		
		//4.使用session对象获取到dao里的Mapper对象
		UserMapper userMapper = session.getMapper(UserMapper.class);
		QueryVo vo = new QueryVo();
		User user = new User();
		user.setUsername("小明");
		vo.setUser(user);
		
		List<User> users = userMapper.findUsersByQueryVo(vo);
		for (User u : users) {
			System.out.println(u);
		}
		
		//-----------------------------------
		session.close();
	}
	//查询user表中数据的数目
	@Test
	public void fun3() throws IOException{
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		
		//4.使用session对象获取到dao里的Mapper对象
		UserMapper userMapper = session.getMapper(UserMapper.class);
		Integer i = userMapper.countUsers();
		System.out.println(i);
		
		//-----------------------------------
		session.close();
	}
	//利用半自动映射查询所有订单
	@Test
	public void fun4() throws IOException{
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		
		//4.使用session对象获取到dao里的Mapper对象
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		List<Orders> orders = mapper.findOrders();
		for (Orders order : orders) {
			System.out.println(order);
		}
		
		//-----------------------------------
		session.close();
	}
	//通过性别或名称来查询用户
	@Test
	public void fun5() throws IOException{
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		
		//4.使用session对象获取到dao里的Mapper对象
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User();
		user.setSex("1");
		user.setUsername("小明");
		List<User> users = mapper.findUsersBySexOrUsername(user);
		for (User u : users) {
			System.out.println(u);
		}
		
		//-----------------------------------
		session.close();
	}
	//根据数个id来查询数个用户(foreach遍历)
	@Test
	public void fun6() throws IOException{
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		
		//4.使用session对象获取到dao里的Mapper对象
		UserMapper mapper = session.getMapper(UserMapper.class);
		Integer[] ids = {16, 25, 28, 29};
		List<User> users = mapper.findUsersByIds(ids);
		for (User u : users) {
			System.out.println(u);
		}
		
		//-----------------------------------
		session.close();
	}
	//一对一关系联合查询，根据订单id查询订单及用户名称
	@Test
	public void fun7() throws IOException{
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		
		//4.使用session对象获取到dao里的Mapper对象
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		Orders order = mapper.findOrderUsernameByOrderId(3);
		System.out.println(order);
		
		//-----------------------------------
		session.close();
	}
	//一对多关系通过用户id来查询用户及其订单信息
	@Test
	public void fun8() throws IOException{
		//1.通过Resources.getResourceAsStream来加载配置文件
		//并将其转换为输入流提供给SqlSessionFactory
		String xmlPath = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(xmlPath);
		//2.创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过SqlSessionFactory来打开sqlSession对象
		SqlSession session = sessionFactory.openSession();
		//-----------------------------------
		
		//4.使用session对象获取到dao里的Mapper对象
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.findUserOrdersById(1);
		System.out.println(user);
		for ( Orders order : user.getOrders()) {
			System.out.println(order.getNumber());
		}

		//-----------------------------------
		session.close();
	}
}
