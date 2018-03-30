package com.stefan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.common.pojo.EasyUITreeNode;
import com.stefan.mapper.TbItemCatMapper;
import com.stefan.pojo.TbItemCat;
import com.stefan.pojo.TbItemCatExample;
import com.stefan.pojo.TbItemCatExample.Criteria;
import com.stefan.service.ItemCatService;

/**
 * 商品分类管理
 * @author 92377
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		//根据parentId查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//把列表转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode result = new EasyUITreeNode();
			//设置属性
			result.setId(tbItemCat.getId());
			result.setText(tbItemCat.getName());
			result.setState(tbItemCat.getIsParent() ? "closed" : "open");
			//添加到结果列表
			resultList.add(result);
		}
		//返回结果
		return resultList;
	}

}
