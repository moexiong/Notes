package com.zsx.d_attributein.e_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//web层的注解
@Controller("studentAction")
public class StudentAction {
	//自动根据类型注入参数的注解
	@Autowired
	private StudentService studentService;
	
	public void execute(){
		System.out.println("web层");
		studentService.addStudent();
	}
	
}
