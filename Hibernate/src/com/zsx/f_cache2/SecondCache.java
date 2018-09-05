package com.zsx.f_cache2;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.Customer;
import com.zsx.domain.Order;
import com.zsx.utils.HibernateUtil;

//二级缓存
public class SecondCache {
	//测试类缓存的存在
	//类缓存中存入的是数据组，不是对象
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//-----------------------------------
		
		Customer customer1 = (Customer) session.get(Customer.class, 6);//发送查询语句
		
		session.clear();//清空一级缓存
		
		Customer customer2 = (Customer) session.get(Customer.class, 6);//不发送查询语句
		
		//-----------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//测试集合缓存的存在
	//存入缓存中的是集合所有者的id，与其集合中所有子对象的id（实际数据存在类缓存中）
	@Test
	public void fun2(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//-----------------------------------
		
		Customer customer1 = (Customer) session.get(Customer.class, 6);
		for(Order o : customer1.getOrders()){//发送查询语句
			System.out.println(o.getName());
		}
		
		session.clear();
		
		Customer customer2 = (Customer) session.get(Customer.class, 6);
		for(Order o : customer2.getOrders()){//不发送查询语句
			System.out.println(o.getName());
		}
		
		//-----------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//测试查询缓存的存在
	//存入缓存中的是最终生成的sql语句，与该语句带回的结果的id（实际数据存在类缓存中）
	@Test
	public void fun3(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//-----------------------------------
		
		Query query1 = session.createQuery("from Customer");//发送查询语句
		// 使用二级(查询)缓存
		// 查询时,会先从二级缓存中取结果.
		// 取不到就执行语句,将结果放入二级查询缓存中
		query1.setCacheable(true);
		List<Customer> list1 = query1.list();
		
		session.clear();//createQuery不用2级缓存本来就会发送2次查询，清空缓存消除未知干扰
		
		Query query2 = session.createQuery("select c from Customer c");//不发送查询语句
		query2.setCacheable(true);
		List<Customer> list2 = query2.list();
		
		//-----------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//时间戳缓存不需要其他配置，开启二级缓存就存在，记录每次操作的时间
}
