package com.zsx.dao;

import java.util.List;

import com.zsx.pojo.Item;
import com.zsx.pojo.QueryVo;

public interface ItemMapper {
	
	//查找所有项
	public List<Item> selectAllItems();
	
	//通过id查找一项
	public Item selectOneItem(Item item);
	
	//通过QueryVo来更新item项
	public Integer updateOneItem(QueryVo vo);
}
