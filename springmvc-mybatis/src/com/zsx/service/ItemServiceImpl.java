package com.zsx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsx.dao.ItemMapper;
import com.zsx.pojo.Item;
import com.zsx.pojo.QueryVo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public List<Item> findAllItems() {
		return itemMapper.selectAllItems();
	}

	@Override
	public Item findItemById(Integer id) {
		Item item = new Item();
		item.setId(id);
		return itemMapper.selectOneItem(item);
	}

	@Override
	public boolean updateItem(QueryVo vo) {
		Integer affectRow = itemMapper.updateOneItem(vo);
		if (affectRow == 0) {
			return false;
		}
		return true;
	}

}
