package com.stefan.content.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stefan.common.jedis.JedisClient;
import com.stefan.common.utils.E3Result;
import com.stefan.common.utils.JsonUtils;
import com.stefan.mapper.TbContentMapper;
import com.stefan.pojo.TbContent;
import com.stefan.pojo.TbContentExample;
import com.stefan.pojo.TbContentExample.Criteria;

/**
 * 内容管理Service
 * @author 92377
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	/**
	 * 增加内容数据
	 */
	@Override
	public E3Result addContent(TbContent content) {
		//将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入到数据库
		contentMapper.insert(content);
		//缓存同步,删除相应缓存
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId() + "");
		return E3Result.ok();
	}
	
	/**
	 * 根据内容分类id查询内容列表
	 */
	@Override
	public List<TbContent> getContentListByCid(long cid) {
		//查询缓存
		try {
			//如果缓存中有直接相应结果
			String json = jedisClient.hget(CONTENT_LIST, cid + "");
			if(StringUtils.isNotBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果没有查询数据库
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//把结果添加到缓存
		try {
			jedisClient.hset("CONTENT_LIST", cid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
