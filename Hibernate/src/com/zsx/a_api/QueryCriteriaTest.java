package com.zsx.a_api;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.zsx.domain.User;

public class QueryCriteriaTest {
	/**
	 * Query 常用方法
	 * 	list();用于接收返回多个结果
	 * 	uniqueResult();用于接收返回一个结果
	 * 	setFirstResult();从下标多少开始查
	 * 	setMaxResults();查询多少条数据
	 */
	@Test
	public void fun1(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//---------------------------------
		
		Query query = session.createQuery("from com.zsx.domain.User");
		//类似limit ?,?
		query.setFirstResult(1);//从第几个开始查
		query.setMaxResults(2);//查几条数据
		
		List<User> list = query.list();
		
		//---------------------------------
		//5关闭资源
		session.close();
		sf.close();
		
		System.out.println("---------------------");
		System.out.println(list);
		System.out.println("---------------------");
	}
	/**
	 * Criteria 常用方法
	 *  list();用于接收返回多个结果
	 *  uniqueResult();用于接收返回一个结果
	 * 	add();添加控制条件
	 * 		参数：Restrictions.?   ?:名称与el相似 语法与sql相似
	 * 			>:gt
	 * 			<:lt
	 * 			=:eq
	 * 			>=:ge
	 * 			<=:le
	 * 			相似:like
	 */
	@Test
	public void fun2(){
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2创建sessionFactory工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3得到一个全新的session对象
		Session session = sf.openSession();
		//---------------------------------
		
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("name", "%o%"));
		List<User> list = criteria.list();
		
		//---------------------------------
		//5关闭资源
		session.close();
		sf.close();
		
		System.out.println("---------------------");
		System.out.println(list);
		System.out.println("---------------------");
	}
}
