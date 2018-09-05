package com.zsx.a_api;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class TransactionTest {
	
	public void fun1(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//------------------------------
		
		//4 transaction的操作
		//打开事务
		Transaction transaction = session.beginTransaction();
		//提交事务
		transaction.commit();
		//回滚事务
		transaction.rollback();
		
		//------------------------------
		//5关闭资源
		session.close();
		sf.close();
	}
	
	@Test
	public void fun2(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session s1 = sf.getCurrentSession();
		//------------------------------
		
		/*Session s2 = sf.getCurrentSession();
		System.out.println(s1==s2);true:2次取到的都是一个session
		*/
		//4 transaction的关闭会自动关闭当前线程的session
		
		/*s2.beginTransaction().commit();
		Session s3 = sf.getCurrentSession();
		System.out.println(s2==s3);false:transaction关闭也关闭了当前session
		*/
		
		//------------------------------
		//5关闭资源
		s1.close();
		sf.close();
	}
}
