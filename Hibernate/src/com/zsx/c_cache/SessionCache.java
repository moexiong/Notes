package com.zsx.c_cache;

import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.User;
import com.zsx.utils.HibernateUtil;

public class SessionCache {
	//验证Session缓存的存在
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//执行第一次查询时由于缓存中不存在而打印sql
		User u1 = (User) session.get(User.class, 1);//从数据库里取出id为1的User对象
		//执行第二次查询时由于缓存中存在而不打印sql
		User u2 = (User) session.get(User.class, 1);//从缓存中找id为1的User对象，如果没有就从数据库取
		
		System.out.println(u1==u2);
		//--------------------------------------
		session.getTransaction().commit();
		session.close();
	}
	
	//Session缓存的快照
	@Test
	public void fun2(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		User user = (User) session.get(User.class, 1);	//持久态
		session.update(user);							//持久态
		//缓存：存在       快照：存在
		//--------------------------------------
		//对比缓存和快照  一致
		session.getTransaction().commit();//不打印update
		session.close();
	}
	//Session缓存的快照
	/**
	 * Hibernate在查数据库时会将对象放入缓存及快照各一份
	 * 在事务提交时对比2份对象是否一致，如果一致就不做处理，不一致就update
	 */
	@Test
	public void fun3(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//--------------------------------------
		//此处设置的User对象与数据库中保持一致
		User user = new User();		//瞬时态
		user.setId(1);				//游离态 有id
		user.setName("tom");		//游离态
		user.setPassword("123");	//游离态
		session.update(user);		//持久态
		//缓存：存在       快照：不存在
		//--------------------------------------
		//对比缓存和快照  不一致
		session.getTransaction().commit();//打印update
		session.close();
	}
}
