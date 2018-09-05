package com.zsx.e_aop.b_cglib;

import org.junit.Test;

import com.zsx.e_aop.a_jdkproxy.DeskService;
import com.zsx.e_aop.a_jdkproxy.MyBeanFactory;

public class EBtest {
	@Test
	public void fun1(){
		DeskService deskService = MyBeanFactory.getService();
		deskService.addDesk();
		deskService.updateDesk();
		deskService.deleteDesk();
	}
}
