package com.zsx.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 自定义异常处理器
 * 当出现异常时，核心控制器会将异常交给异常处理器来处理
 * 在这里可以自定义对异常的操作
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e) {
		//obj错误的详细位置，包名+类名+方法名（参数）
		//e异常的类型
		ModelAndView mav = new ModelAndView();
		if (e instanceof CustomException) {//用于判断是否是自定义的哪一种异常
			CustomException ce = (CustomException) e;
			mav.addObject("error", ce.getMessage());
		}else {//都不属于时的未知异常
			mav.addObject("error", "未知异常！");
		}
		mav.setViewName("error");
		return mav;
	}

}
