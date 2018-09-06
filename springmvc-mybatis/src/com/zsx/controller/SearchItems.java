package com.zsx.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zsx.exception.CustomException;
import com.zsx.pojo.Item;
import com.zsx.pojo.QueryVo;
import com.zsx.service.ItemService;
/*
 * @RequestMapping的三种用法
 * 1.放在类名上方，可以缩小范围（提取出公共路径）
 * 2.放在方法名上方，可以指明请求的路径
 * 3.可以加参数，限定请求的方式
 * 
 * 方法的返回值的三种
 * 1.ModelAndView：万能式，包装了视图与数据，最基础但不建议使用
 * 2.String：类似于struts2中的action，参数为model（封装数据，使用addAttribute），返回值为结果视图，因为解耦，推荐使用
 * 			转发到itemList.jsp-->return forward:/item/itemList.action
 * 			转发到itemList.jsp-->return redirect:/item/itemList.action
 * 3.void：参数为model（封装数据，使用addAttribute），不跳转视图，用于ajax异步请求
 */
@Controller
public class SearchItems {
	
	@Autowired
	private ItemService itemService;
	
	//显示商品列表
	@RequestMapping("/item/itemlist.action")
//	public ModelAndView itemList(){
	public String itemList(Model model) throws CustomException{
//		ModelAndView mav = new ModelAndView();
		
		List<Item> items = itemService.findAllItems();
		
		//自定义异常处理
		if (items == null) {
			throw new CustomException("这是一个自定义的异常！！");
		}
		
		model.addAttribute(items);
//		mav.addObject("itemList", items);
//		mav.setViewName("itemList");
//		return mav;
		return "itemList";
	}
	
	//参数注入1.HttpServletRequest request，response，session，model四个默认参数
	//		2.基本类型Integer int,String,Float float,Double double......
	//编辑单个商品,自动注入到形参基本类型，需要页面传参名与形参名一致
	@RequestMapping("/item/itemEdit.action")
	public ModelAndView itemEdit(Integer id){
		ModelAndView mav = new ModelAndView();
		
		Item item = itemService.findItemById(id);
		
		mav.addObject("item", item);
		mav.setViewName("editItem");
		return mav;
	}
	
	//		3.POJO类型，需要表单中各个  name属性名  与  POJO中的字段名  一致
	//		4.QueryVo包装类类型，需要表单中各个  name属性名  与  QueryVo中的字段名.POJO中的字段名  一致
	//		5.数组类型，CheckBox的名称  与  数组的形参名  相同，可以放入QueryVo中
	//		6.List类型，不能直接传入List，必须放入QueryVo中，List中存入item对象时，页面中name值：List的名称[索引].item属性
	//上传文件时，参数为MultipartFile接口类型，  形参  必须与  页面中name属性名  一致
	@RequestMapping("/item/updateitem.action")
	public String itemUpdate(QueryVo vo, MultipartFile pictureFile) throws Exception{
		//导入文件上传jar包之后
		//去掉UUID之间的- 生成文件名
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		//得到文件的拓展名
		String extension = FilenameUtils.getExtension(pictureFile.toString());
		//合成完整的文件名，带有拓展名
		String fullName = name + "." + extension;
		//存入到本地路径
//		pictureFile.transferTo(new File("E:\\图片\\temp\\" + fullName));
		pictureFile.transferTo(new File("/WEB-INF/upload/" + fullName));
		//存入文件名到服务器
		vo.getItem().setPic(fullName);
		
		boolean updateItem = itemService.updateItem(vo);
		System.out.println(updateItem);
		
		return "redirect:/item/itemList.action";
	}
	
}
