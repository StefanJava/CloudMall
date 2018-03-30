package com.stefan.content.service;

import java.util.List;

import com.stefan.common.utils.E3Result;
import com.stefan.pojo.TbContent;

public interface ContentService {
	E3Result addContent(TbContent content);
	List<TbContent> getContentListByCid(long cid);
}
