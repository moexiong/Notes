package com.zsx.e_aop.a_jdkproxy;

import org.junit.Test;

public class EAtest {
	@Test
	public void fun1(){
		DeskService deskService = MyBeanFactory.getService();
		deskService.addDesk();
		deskService.updateDesk();
		deskService.deleteDesk();
	}
	
}
