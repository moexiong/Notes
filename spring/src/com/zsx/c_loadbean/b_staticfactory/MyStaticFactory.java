package com.zsx.c_loadbean.b_staticfactory;

import com.zsx.a_ioc.UserService;
import com.zsx.a_ioc.UserServiceImpl;

public class MyStaticFactory {
	//自定义静态工厂
	public static UserService getService(){
		return new UserServiceImpl();
	}
	
}
