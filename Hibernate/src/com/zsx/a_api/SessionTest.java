package com.zsx.a_api;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.User;
//session对象的操作
public class SessionTest {
	/**
	 * 增
	 */
	@Test
	public void fun1(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//4对数据库的操作
			User user = new User();
			user.setName("hello");
			user.setPassword("123");
			session.save(user);
		//5关闭资源
		session.close();
		sf.close();
		
		System.out.println("---------------------");
		System.out.println(user);
		System.out.println("---------------------");
	}
	
	/**
	 * 改
	 */
	@Test
	public void fun2(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//开启事务
		Transaction ts = session.beginTransaction();
		//4对数据库的操作
			//更新需要得到要删除的对象的主键
			User user = (User) session.get(User.class, 1);//参数：实体类   要取出的对象主键
			user.setName("tom");
			session.update(user);
		//提交事务
		ts.commit();
		//5关闭资源
		session.close();
		sf.close();
		
		System.out.println("---------------------");
		System.out.println(user);
		System.out.println("---------------------");
	}
	
	/**
	 * 删
	 */
	@Test
	public void fun3(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//开启事务
		Transaction ts = session.beginTransaction();
		//4对数据库的操作
			//删除只是为了得到要删除的对象的主键
			//第一种：User user = (User) session.get(User.class, 1);//参数：实体类   要取出的对象主键
			//第二种：直接创建一个新对象，设置主键
			User user = new User();
			user.setId(1);
			session.delete(user);
		//提交事务
		ts.commit();
		//5关闭资源
		session.close();
		sf.close();
		
		System.out.println("---------------------");
		System.out.println(user);
		System.out.println("---------------------");
	}

	/**
	 * get查
	 */
	@Test
	public void fun4(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//开启事务
		Transaction ts = session.beginTransaction();
		//4对数据库的操作
			//get查询：没有id返回null
			User user = (User) session.get(User.class, 1);//参数：实体类   要取出的对象主键
			System.out.println(user);
		//提交事务
		ts.commit();
		//5关闭资源
		session.close();
		sf.close();
		
		System.out.println("---------------------");
		System.out.println(user);
		System.out.println("---------------------");
	}
	
	/*
	 * get查询：直接向数据库发送sql执行查询
	 * load查询：延迟查询，创建一个代理对象保存状态，在需要的时候在向数据库发送sql查询
	 * */
	
	/**
	 * load查
	 */
	@Test
	public void fun5(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//开启事务
		Transaction ts = session.beginTransaction();
		//4对数据库的操作
			//load查询：没有id抛异常
			User user = (User) session.load(User.class, 1);//参数：实体类   要取出的对象主键
			System.out.println(user);
		//提交事务
		ts.commit();
		//5关闭资源
		session.close();
		sf.close();
		
		System.out.println("---------------------");
		//System.out.println(user);//load查询会报错
		System.out.println("---------------------");
	}

	/**
	 * 查询所有
	 */
	@Test
	public void fun6(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//开启事务
		Transaction ts = session.beginTransaction();
		//4对数据库的操作
		//----------------------------------------
			//1  利用HQL语言查询所有，Hibernate Query Language
			//	createQuery(); 参数：HQL语句
			//	自动封装对象
			//Query query = session.createQuery("from com.zsx.domain.User");
			//List<User> list = query.list();
		//----------------------------------------
			//2  利用Criteria查询所有
			//	自动封装对象
			//Criteria criteria = session.createCriteria(User.class);
			//List<User> list = criteria.list();
		//----------------------------------------
			//3  利用原生的sql语句进行查询
			//	手动封装对象 调用addEntity方法
			SQLQuery query = session.createSQLQuery("select * from t_user");
			//封装对象
			query.addEntity(User.class);
			List<User> list = query.list();
			//不封装
			/*List<Object[]> list = query.list();
			for(Object[] objs : list){
				System.out.println(Arrays.toString(objs));
			}*/
		//----------------------------------------
		//提交事务
		ts.commit();
		//5关闭资源
		session.close();
		sf.close();
		
		System.out.println("---------------------");
		System.out.println(list);
		System.out.println("---------------------");
	}
	/**
	 * 所有的方法：
	 * session.save(对象) 保存
	 * session.update(对象) 更新
	 * session.delete(对象) 删除
	 * session.get(指定查询的对象类，主键) 查询一个  立即加载
	 * session.load(指定查询的对象类，主键) 查询一个  延迟加载
	 * session.createQuery(HQL语句) 查询所有
	 * session.createCriteria(指定查询的对象类) 查询所有
	 * session.createSQLQuery(SQL语句) 查询所有
	 * 		   addEntity(指定封装的对象类)
	 */
}
