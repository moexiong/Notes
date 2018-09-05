package com.zsx.domain;

import java.util.HashSet;
import java.util.Set;
//一对多关系中一个
public class Customer {
	
	private Integer id;
	private String name;
	private Set<Order> orders = new HashSet<Order>();//包含多个对象时=》采用集合Set并初始化
	//无参构造 在HQL查询中需要
	public Customer() {
	}
	//有参构造 在HQL查询中用到
	public Customer(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", orders=" + orders
				+ "]";
	}
	
}
