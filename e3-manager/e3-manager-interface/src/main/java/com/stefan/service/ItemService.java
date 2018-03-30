package com.stefan.service;

import com.stefan.common.pojo.EasyUIDataGridResult;
import com.stefan.common.utils.E3Result;
import com.stefan.pojo.TbItem;
import com.stefan.pojo.TbItemDesc;

public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	E3Result addItem(TbItem item, String desc);
	TbItemDesc getItemDescById(long itemId);
}
