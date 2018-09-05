package com.zsx.e_aop.e_aspectjxml;

public class PenServiceImpl implements PenService {

	@Override
	public void addPen() {
		System.out.println("addPen方法执行了。。。");
	}

	@Override
	public String updatePen() {
		System.out.println("updatePen方法执行了。。。");
		//int i = 1/0;
		return "---ok---";
	}

	@Override
	public void deletePen() {
		System.out.println("deletePen方法执行了。。。");
	}

}
