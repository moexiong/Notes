package com.zsx.c_loadbean.c_factory;

import com.zsx.a_ioc.UserService;
import com.zsx.a_ioc.UserServiceImpl;

public class MyFactory {
	//自定义非静态工厂
	public UserService getService(){
		return new UserServiceImpl();
	}
	
}
