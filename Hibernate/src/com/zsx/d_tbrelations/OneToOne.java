package com.zsx.d_tbrelations;

import org.hibernate.classic.Session;
import org.junit.Test;

import com.zsx.domain.Address;
import com.zsx.domain.Company;
import com.zsx.utils.HibernateUtil;

public class OneToOne {
	//一对一关系采用了默认的主键同步策略
	@Test
	public void fun1(){
		Session session = HibernateUtil.opSession();
		session.beginTransaction();
		//----------------------------------
		
		Company company = new Company();
		company.setName("itheima");
		
		Address address = new Address();
		address.setName("jinyanlong");
		
		//company.setAddress(address);//貌似不能维护关系
		address.setCompany(company);//貌似只有主键作为外键的一方的才能维护关系
		session.save(company);
		session.save(address);
		
		Company c = (Company) session.get(Company.class, 7);
		System.out.println(c);
		
		//----------------------------------
		session.getTransaction().commit();
		session.close();
	}
	
}
