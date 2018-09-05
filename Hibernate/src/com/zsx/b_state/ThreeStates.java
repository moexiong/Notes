package com.zsx.b_state;

import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.User;
import com.zsx.utils.HibernateUtil;

public class ThreeStates {
	/**
	 * 三种状态：
	 * 	瞬时态 1.与Hibernate没有关联   2.与数据库没有关联（在数据库中没有id）
	 * 	持久态 1.与Hibernate有关联	2.与数据库有关联（在数据库中有id）	持久化对象自动与数据库同步
	 * 	游离态 1.与Hibernate没有关联   2.与数据库有关联（在数据库中有id）
	 */
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//-----------------------------------------
		User user = new User();				//瞬时态
		user.setName("state");				//瞬时态
		user.setPassword("1234");			//瞬时态
		
		session.save(user);					//持久态  save方法的目的是为了得到id
		//-----------------------------------------
		session.getTransaction().commit();	//持久态
		
		session.close();					//游离态
	}
	
}
