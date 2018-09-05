package com.zsx.dao_mapper;

import java.util.List;

import com.zsx.pojo.Orders;

public interface OrderMapper {
	//由于数据库与POJO有一个不同的字段，需要使用resultMap来半自动映射
	//查询所有订单
	public List<Orders> findOrders();
	
	//一对一关系联合查询，根据订单id查询订单及用户名称
	public Orders findOrderUsernameByOrderId(Integer id);
}
