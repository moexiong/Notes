package com.zsx.a_api;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.User;

public class TestMain {
	@Test
	public void test1(){
		//1.得到配置文件信息   configure()有参数的重载，参数为需要得到的配置文件
		//	默认为src目录下的hibernate.cfg.xml
		Configuration conf = new Configuration().configure();
		//2.根据拿到的信息构造session工厂
		SessionFactory sf = conf.buildSessionFactory();
		//3.根据session工厂开启session对象
		/**
		 * sessionfactory : 就好比  c3p0连接池
		 * session : 就好比  连接池中的一个连接
		 * sf.openSession : 打开一个全新的session对象
		 * sf.getCurrentSession : 打开一个当前的session对象，如果没有就创建一个全新的session对象
		 * 			使用必须配置  ==》<property name="hibernate.current_session_context_class">thread</property>
		 */
		Session session = sf.openSession();
		//4.用session对象通过hibernate操作数据库
		User u = new User();
		u.setName("test1");
		u.setPassword("123");
		session.save(u);
		//5.关闭所有打开的资源
		session.close();
		sf.close();
	}
	
}
