package com.zsx.e_loadstrategy;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.Customer;
import com.zsx.utils.HibernateUtil;

//HQL连接查询
public class HQLConnection {
	//交叉查询，笛卡尔积
	//不使用，他会产生排列组合结果，非常多
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//------------------------------------
		
		Query query = session.createQuery("from Customer c, Order o");
		
		List<Object[]> list = query.list();
		
		for(Object[] o : list){
			System.out.println(Arrays.toString(o));
		}
		
		//------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//内连接
	@Test
	public void fun2(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//------------------------------------
		//隐式内连接
		Query q1 = session.createQuery("from Customer c, Order o where c = o.customer");
		//显式内连接   非迫切=>就是将查询的结果一起存入数组中返回
		//inner join + 对象的连接字段
		Query q2 = session.createQuery("from Customer c inner join c.orders");
		//显式内连接   迫切fetch=>就是将查询的结果封装到一个对象中返回
		Query q3 = session.createQuery("from Customer c inner join fetch c.orders");
		
		List<Object[]> l1 = q1.list();
		//将Customer和Order一起存入数组中返回
		List<Object[]> l2 = q2.list();
		//封装到Customer中
		List<Customer> l3 = q3.list();
		
		for(Object[] o : l1){
			System.out.println(Arrays.toString(o));
		}
		System.out.println("----------------------------");
		for(Object[] o : l2){
			System.out.println(Arrays.toString(o));
		}
		System.out.println("----------------------------");
		for(Customer c : l3){
			System.out.println(c);
		}
		
		//------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//外连接
	@Test
	public void fun3(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//------------------------------------
		//左外连接 非迫切
		Query q1 = session.createQuery("from Customer c left join c.orders");
		//左外连接 迫切
		Query q2 = session.createQuery("from Customer c left join fetch c.orders");
		//右外连接 非迫切
		Query q3 = session.createQuery("from Customer c right join c.orders");
		//右外连接 迫切
		Query q4 = session.createQuery("from Customer c right join fetch c.orders");
		
		List<Object[]> l1 = q1.list();
		List<Customer> l2 = q2.list();
		List<Object[]> l3 = q3.list();
		List<Customer> l4 = q4.list();
		
		for(Object[] o : l1){
			System.out.println(Arrays.toString(o));
		}
		System.out.println("----------------------------");
		for(Customer c : l2){
			System.out.println(c);
		}
		System.out.println("----------------------------");
		for(Object[] o : l3){
			System.out.println(Arrays.toString(o));
		}
		System.out.println("----------------------------");
		for(Customer c : l4){
			System.out.println(c);
		}
		
		//------------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
