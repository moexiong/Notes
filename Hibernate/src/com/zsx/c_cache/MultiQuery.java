package com.zsx.c_cache;

import java.util.List;

import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.User;
import com.zsx.utils.HibernateUtil;

public class MultiQuery {
	//证明虽然HQL语句查询时使用了一级缓存
	//但是HQL仍然会发送sql语句
	//与Criteria相同
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//打印sql语句
		List<User> list1 = session.createQuery("from User").list();
		//打印sql语句
		List<User> list2 = session.createQuery("from User").list();
		//打印sql语句
		List<User> list3 = session.createQuery("from User").list();
		//不打印sql语句
		User user = (User) session.get(User.class, 1);
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//证明虽然SQL语句查询时使用了一级缓存
	//但是SQL仍然会发送sql语句
	//使用addEntity封装之后会将查询结果放入缓存中
	@Test
	public void fun2(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//打印sql语句
		List<User> list1 = session.createSQLQuery("select * from t_user").addEntity(User.class).list();
		//打印sql语句
		List<User> list2 = session.createSQLQuery("select * from t_user").addEntity(User.class).list();
		//打印sql语句
		List<User> list3 = session.createSQLQuery("select * from t_user").addEntity(User.class).list();
		//不打印sql语句
		User user = (User) session.get(User.class, 1);
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//不使用addEntity封装不会将查询结果放入缓存中
	@Test
	public void fun3(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//打印sql语句
		List<User> list1 = session.createSQLQuery("select * from t_user").list();
		//打印sql语句
		List<User> list2 = session.createSQLQuery("select * from t_user").list();
		//打印sql语句
		List<User> list3 = session.createSQLQuery("select * from t_user").list();
		//打印sql语句
		User user = (User) session.get(User.class, 1);
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//HQL与SQL语句不管查询单个还是多个都会使用一级缓存，也会发送sql语句
	@Test
	public void fun4(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//打印sql语句
		User user1 = (User) session.createQuery("from User where id = 1").uniqueResult();
		//打印sql语句
		User user2 = (User) session.createQuery("from User where id = 1").uniqueResult();
		System.out.println("-----------------------");
		//打印sql语句
		User user3 = (User) session.createSQLQuery("select * from t_user where id = 1").addEntity(User.class).uniqueResult();
		//打印sql语句
		User user4 = (User) session.createSQLQuery("select * from t_user where id = 1").addEntity(User.class).uniqueResult();
		//不打印sql语句
		User user = (User) session.get(User.class, 1);
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
