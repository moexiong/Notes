package com.zsx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyIntercepter implements HandlerInterceptor {
	/*多个拦截器时
	 * 前方法执行依赖：上一个拦截器的前方法执行成功    	 配置顺序
	 * 后方法执行依赖：所有拦截器的前方法执行成功	 	 配置逆序
	 * 页面渲染后方法执行依赖：对应拦截器中前方法执行成功 配置逆序
	 * */
	
	//拦截前方法，在方法执行前执行，是否放行动作方法
	@Override
	public boolean preHandle(HttpServletRequest resqest, HttpServletResponse response,
			Object object) throws Exception {
		System.out.println("前方法执行--------------");
		return true;
	}
	//拦截后方法，在方法执行后执行
	@Override
	public void postHandle(HttpServletRequest resqest, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
		System.out.println("后方法执行--------------");
	}
	//页面渲染后方法，在拦截后方法执行
	@Override
	public void afterCompletion(HttpServletRequest resqest,
			HttpServletResponse response, Object object, Exception e)
			throws Exception {
		System.out.println("页面渲染后方法执行--------------");
	}
}
