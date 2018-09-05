package com.zsx.e_loadstrategy;

import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.Customer;
import com.zsx.domain.Order;
import com.zsx.utils.HibernateUtil;

public class LoadingStrategy {
	//类级别加载策略   在<class标签属性里>
	//默认   lazy=>true  相当于 load()方法延迟加载，使用时在加载
	//    lazy=>false 相当于 get()方法不延迟加载
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//---------------------------------
		
		Customer c = (Customer) session.load(Customer.class, 6);//load()会延迟加载  没有加载Customer，只是生成代理对象
		
		System.out.println(c.getName());//调用时才加载Customer
		
		//---------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//关联级别加载策略  在<set标签属性里>
	//默认   lazy=>true  延迟加载，使用时在加载
	// 	  lazy=>false 直接加载
	@Test
	public void fun2(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//---------------------------------
		
		Customer c = (Customer) session.get(Customer.class, 6);//这里用get()直接加载对象，没有加载Orders
		
		for(Order o : c.getOrders()){//c.getOrders()用到了Orders属性才加载所有的Order对象
			System.out.println(o.getName());
		}
		
		//---------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
