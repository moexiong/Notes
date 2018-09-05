package com.zsx.e_aop.a_jdkproxy;

public class DeskServiceImpl implements DeskService {

	@Override
	public void addDesk() {
		System.out.println("addDesk方法执行了。。。（切入点）");
	}

	@Override
	public void updateDesk() {
		System.out.println("updateDesk方法执行了。。。（切入点）");
	}

	@Override
	public void deleteDesk() {
		System.out.println("deleteDesk方法执行了。。。（连接点）");
	}

}
