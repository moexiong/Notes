package com.zsx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zsx.pojo.Item;

//配置注解 表明是控制层
@Controller
public class ItemQuery {
	
	//配置处理映射交由处理器映射器（处理器映射器-三大组件之一）
	//该方法返回的是com.zsx.controller.ItemQuery.itemList（包名+类名+方法名）
	@RequestMapping("/item/itemlist.action")
	public ModelAndView itemList(){
		//ModerAndView中model表示的是数据模型，view表示的是视图
		//ModerAndView将model交由处理器适配器（处理器适配器-三大组件之一），在分配到处理器（我们写的方法）进行数据封装
		//ModerAndView将view交由视图解析器（视图处理器-三大组件之一），在分配到视图（我们写的jsp）进行渲染（填充数据）
		ModelAndView mav = new ModelAndView();
		
		List<Item> items = new ArrayList<>();
		items.add(new Item(1, "1华为 荣耀8", 2399f, "质量好！1", new Date()));
		items.add(new Item(2, "2华为 荣耀8", 2399f, "质量好！2", new Date()));
		items.add(new Item(3, "3华为 荣耀8", 2399f, "质量好！3", new Date()));
		items.add(new Item(4, "4华为 荣耀8", 2399f, "质量好！4", new Date()));
		items.add(new Item(5, "5华为 荣耀8", 2399f, "质量好！5", new Date()));
		items.add(new Item(6, "6华为 荣耀8", 2399f, "质量好！6", new Date()));
		
		//向model中添加数据，类似request域，采用键值对
		mav.addObject("itemList", items);
		
		//向view中添加视图
		//mav.setViewName("/WEB-INF/jsp/itemList.jsp");//未配置视图解析器前后缀
		mav.setViewName("itemList");//配置视图解析器前缀（/WEB-INF/jsp/）后缀（.jsp）
		return mav;
	}
	
}
