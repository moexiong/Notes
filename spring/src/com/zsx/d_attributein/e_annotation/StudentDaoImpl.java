package com.zsx.d_attributein.e_annotation;

import org.springframework.stereotype.Repository;
//dao层的注解
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

	@Override
	public void saveStudent() {
		System.out.println("dao层");
	}

}
