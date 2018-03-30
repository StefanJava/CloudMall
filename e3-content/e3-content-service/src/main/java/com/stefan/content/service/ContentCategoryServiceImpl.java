package com.stefan.content.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.common.pojo.EasyUITreeNode;
import com.stefan.common.utils.E3Result;
import com.stefan.mapper.TbContentCategoryMapper;
import com.stefan.pojo.TbContentCategory;
import com.stefan.pojo.TbContentCategoryExample;
import com.stefan.pojo.TbContentCategoryExample.Criteria;

/**
 * 内容分类管理Service
 * @author 92377
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		//根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
		//转换成EasyUITreeNode的列表
		List<EasyUITreeNode> nodeList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : catList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			//添加到列表
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public E3Result addContentCategory(long parentId, String name) {
		//创建一个tb_content_category表对应的pojo对象
		TbContentCategory tbContentCategory = new TbContentCategory();
		//设置pojo属性
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setName(name);
		tbContentCategory.setStatus(1);//1正常,2上除
		//默认排序就是1
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		//插入到数据库
		contentCategoryMapper.insert(tbContentCategory);
		//判断父节点的isparent属性,如果不是true改为true
		//根据parentId查询父节点
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (! parent.getIsParent()) {
			parent.setIsParent(true);
			//更新到数据库
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		//返回结果,返回E2Result包含pojo
		return E3Result.ok(tbContentCategory);
	}

}
