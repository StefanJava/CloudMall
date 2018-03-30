package com.stefan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stefan.common.utils.E3Result;
import com.stefan.search.service.SearchItemService;

/**
 * 导入商品数据到索引库
 * @author 92377
 *
 */
@Controller
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemList() {
		E3Result result = searchItemService.importAllItems();
		return result;
	}
	
}
