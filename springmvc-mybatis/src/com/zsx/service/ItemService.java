package com.zsx.service;

import java.util.List;

import com.zsx.pojo.Item;
import com.zsx.pojo.QueryVo;

public interface ItemService {
	
	//查找所有项
	public List<Item> findAllItems();
	
	//通过id查找一项
	public Item findItemById(Integer id);
	
	//通过QueryVo来更新item项
	public boolean updateItem(QueryVo vo);
}
