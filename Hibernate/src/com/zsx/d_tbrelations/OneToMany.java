package com.zsx.d_tbrelations;

import java.util.Set;

import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.Customer;
import com.zsx.domain.Order;
import com.zsx.utils.HibernateUtil;

public class OneToMany {
	
	/**
	 * Customer inverse:false=》Customer没有放弃外键，由Customer和Order双方共同维护外键
	 * 	此时会先打印3条insert语句=》保存对象，维护外键
	 * 	之后又会打印2条update语句=》维护外键
	 * 弊端：重复维护
	 */
	/**
	 * Customer inverse:true=》此时Customer已经放弃了维护外键，全权交由Order来维护
	 * 此时只会打印3条insert语句
	 */
	//Customer inverse属性=>true
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//----------------------------------
		
		Customer customer = new Customer();
		customer.setName("study");
		
		Order o1 = new Order();
		o1.setName("paper");
		
		Order o2 = new Order();
		o2.setName("pencil");
		//不需要Customer来维护外键，只有一方就足够了
		//customer.getOrders().add(o1);//由Customer来维护关系（外键）
		//customer.getOrders().add(o2);//由Customer来维护关系（外键）
		
		o1.setCustomer(customer);//由Order来维护关系（外键）
		o2.setCustomer(customer);//由Order来维护关系（外键）
		
		session.save(customer);
		session.save(o1);
		session.save(o2);
		
		//----------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//Customer inverse属性=>true
	@Test
	public void fun2(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//----------------------------------
		
		Customer customer = (Customer) session.get(Customer.class, 1);
		//由于Customer放弃了维护主键，所以在删除时必须先取出Order对象，让Order对象放弃该对象的引用（外键），设置为null，才可删除
		Set<Order> set = customer.getOrders();
		for(Order o : set){
			o.setCustomer(null);
		}
		//由于Customer放弃了维护主键，直接删除会报错，必须执行上方代码
		session.delete(customer);
		
		//----------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//Customer inverse属性=>true
	//Customer cascade属性=>save-update  save update不解释
	//这种操作的弊端：Order无法维护外键，级联存入的外键没有值
	//解决办法：设置Customer inverse属性=>false
	@Test
	public void fun3(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//----------------------------------
		
		Customer customer = new Customer();
		customer.setName("jack");
		
		Order o1 = new Order();
		o1.setName("banana");
		
		Order o2 = new Order();
		o2.setName("tomato");
		
		customer.getOrders().add(o1);//Customer虽然放弃了维护关系（外键），但是设置了属性之后可以同步级联操作
		customer.getOrders().add(o2);//Customer虽然放弃了维护关系（外键），但是设置了属性之后可以同步级联操作
		//存入的Order的外键cid为null
		session.save(customer);
		//在Customer中设置了级联保存之后，当Customer对象保存时，自动保存与Customer相关的订单对象
		//session.save(o1);
		//session.save(o2);
		
		//----------------------------------
		session.getTransaction().commit();
		session.close();
	}
	//Customer inverse属性=>true
	//Customer cascade属性=>delete
	@Test
	public void fun4(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//----------------------------------
		
		Customer customer = (Customer) session.get(Customer.class, 2);
		//删除Customer会自动删除与Customer有关联的Order
		session.delete(customer);
		
		//----------------------------------
		session.getTransaction().commit();
		session.close();
	}
}
