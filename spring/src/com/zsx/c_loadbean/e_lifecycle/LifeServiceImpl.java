package com.zsx.c_loadbean.e_lifecycle;

public class LifeServiceImpl implements LifeService{

	@Override
	public void live() {
		System.out.println("c_e_lifecycle --> live执行了");
	}
	//bean的生命周期的初始化，在调用方法之前
	public void initLife(){
		System.out.println("初始化完成！");
	}
	//bean的生命周期的初始化，在调用方法之后，容器被关闭时
	public void destroyLife(){
		System.out.println("销毁完成！");
	}
}
