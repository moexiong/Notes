package com.zsx.e_loadstrategy;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.Customer;
import com.zsx.utils.HibernateUtil;

//HQL一般查询
public class HQLQuery {
	//HQL查询所有
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//Customer是实体的完整类名，短类名也行
		Query q1 = session.createQuery("from Customer");
		//c是实体Customer的别名
		Query q2 = session.createQuery("from Customer c");
		//完整的语句
		Query q3 = session.createQuery("select c from Customer c");
		
		List<Customer> l1 = q1.list();
		List<Customer> l2 = q2.list();
		List<Customer> l3 = q3.list();
		
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(l3);
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//HQL查询所有对象的部分属性
	@Test
	public void fun2(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//直接将查出来的数据存入数组中
		//实体名(别名).字段      点后面是实体中的字段，不是数据库中的列
		Query query = session.createQuery("select c.id,c.name from Customer c");

		List<Object[]> list = query.list();
		
		for(Object[] o : list){
			System.out.println(Arrays.toString(o));
		}
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//投影查询
	@Test
	public void fun3(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//会将查出来的数据封装进Customer对象中
		//需要在实体中创建构造方法Customer(id,name)
		Query query = session.createQuery("select new Customer(c.id,c.name) from Customer c");
		
		List<Customer> list = query.list();
		
		System.out.println(list);
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//排序查询
	@Test
	public void fun4(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//asc 升序
		Query q1 = session.createQuery("from Customer c order by c.id asc");
		//desc 降序
		Query q2 = session.createQuery("from Customer c order by c.id desc");
		
		List<Customer> l1 = q1.list();
		List<Customer> l2 = q2.list();
		
		System.out.println(l1);
		System.out.println(l2);
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//分页查询
	@Test
	public void fun5(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		
		Query query = session.createQuery("from Customer c order by c.id desc");
		//limit ?,? => setFirstResult(),setMaxResults()
		query.setFirstResult(1);//起始查询的索引
		query.setMaxResults(2);//每页查询的数目
		
		List<Customer> list = query.list();
		
		System.out.println(list);
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//绑定参数
	@Test
	public void fun6(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//1.占位符  ？
		Query q1 = session.createQuery("from Customer c where c.id = ?");
		//setInteger(占位符的索引，占位符的值);占位符的索引是从0开始
		q1.setInteger(0, 4);
		//2.占位符  :自定义字符串
		Query q2 = session.createQuery("from Customer c where c.id = :zidingyi");
		//setInteger(自定义占位符的名称，占位符的值);
		q2.setInteger("zidingyi", 4);
		
		Customer c1 = (Customer) q1.uniqueResult();
		Customer c2 = (Customer) q2.uniqueResult();
		
		System.out.println(c1);
		System.out.println(c2);
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//聚合函数
	@Test
	public void fun7(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		
		Query q1 = session.createQuery("select count(*) from Customer c");
		Query q2 = session.createQuery("select sum(c.id) from Customer c");
		Query q3 = session.createQuery("select avg(c.id) from Customer c");
		Query q4 = session.createQuery("select max(c.id) from Customer c");
		Query q5 = session.createQuery("select min(c.id) from Customer c");
		
		Object o1 = q1.uniqueResult();
		Object o2 = q2.uniqueResult();
		Object o3 = q3.uniqueResult();
		Object o4 = q4.uniqueResult();
		Object o5 = q5.uniqueResult();
		
		System.out.println("count:"+o1+" sum:"+o2+" avg:"+o3+" max:"+o4+" min:"+o5);
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//分组查询
	@Test
	public void fun8(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		
		Query q1 = session.createQuery("select o.customer,count(o) from Order o group by o.customer");
		Query q2 = session.createQuery("select o.customer,count(o) from Order o group by o.customer having count(o) > 1");
		
		List<Object[]> l1 = q1.list();
		List<Object[]> l2 = q2.list();
		
		for(Object[] o : l1){
			System.out.println(Arrays.toString(o));
		}
		System.out.println("-------------------------------");
		for(Object[] o : l2){
			System.out.println(Arrays.toString(o));
		}
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//命名查询
	@Test
	public void fun9(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//局部配置的HQL查询语句需要加上配置所在的完整路径名
		Query q1 = session.getNamedQuery("com.zsx.domain.Customer.bbb");
		//全局配置的HQL查询语句不需要任何配置，直接输入name的值即可
		Query q2 = session.getNamedQuery("aaa");
		
		List<Object> l1 = q1.list();
		List<Object> l2 = q2.list();
		
		System.out.println(l1);
		System.out.println("--------------------------------");
		System.out.println(l2);
		
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
