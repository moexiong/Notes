package com.zsx.d_attributein.e_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
//service层的注解
@Service
public class StudentServiceImpl implements StudentService {
	//2个注解一起使用代表使用指定的引用参数注入
	//使用参数注入需要setter方法
	@Autowired
	@Qualifier("studentDao")
	private StudentDao studentDao;
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public void addStudent() {
		System.out.println("service层");
		studentDao.saveStudent();
	}

}
