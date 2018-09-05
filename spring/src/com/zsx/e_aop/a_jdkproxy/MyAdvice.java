package com.zsx.e_aop.a_jdkproxy;
//advice
public class MyAdvice {

	public void adBefore(){
		System.out.println("adBefore执行了。。。（通知）");
	}
	
	public void adAfter(){
		System.out.println("adAfter执行了。。。（通知）");
	}
	
}
