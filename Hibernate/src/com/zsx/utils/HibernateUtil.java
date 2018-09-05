package com.zsx.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateUtil {
	
	private static SessionFactory sf;
	
	static {
		//1加载配置
		Configuration conf = new Configuration().configure();
		//2初始化sessionFactory对象
		sf = conf.buildSessionFactory();
		//3在执行结束时关闭sessionFactory,释放资源
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				sf.close();
				System.out.println("释放资源！");
			}
		}));
	}
	
	public static Session opSession(){
		return sf.openSession();
	}
	
	public static Session gcSession(){
		return sf.getCurrentSession();
	}
	
	/*public static void main(String[] args) {
		System.out.println(opSession());
	}*/
	
}
