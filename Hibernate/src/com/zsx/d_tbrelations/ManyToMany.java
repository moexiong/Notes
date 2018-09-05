package com.zsx.d_tbrelations;

import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.Course;
import com.zsx.domain.Student;
import com.zsx.utils.HibernateUtil;

public class ManyToMany {
	//Student->inverse:false cascade:save-update  维护关系的一方进行级联操作
	//Course ->inverse:true  cascade:
	//由Student来维护外键关系，多对多关系 必须有一方放弃维护关系
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//----------------------------------
		
		Student s1 = new Student();
		s1.setName("tom");
		
		Student s2 = new Student();
		s2.setName("jerry");
		
		Course c1 = new Course();
		c1.setName("struts2");
		
		Course c2 = new Course();
		c2.setName("hibernate");
		
		Course c3 = new Course();
		c3.setName("spring");
		//学生1维护关系
		s1.getCourses().add(c1);
		s1.getCourses().add(c2);
		s1.getCourses().add(c3);
		//学生2维护关系
		s2.getCourses().add(c1);
		s2.getCourses().add(c2);
		s2.getCourses().add(c3);
		
		session.save(s1);
		session.save(s2);
		
		//----------------------------------
		session.getTransaction().commit();
		session.close();
	}
	
}
