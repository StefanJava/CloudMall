package com.stefan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stefan.common.utils.E3Result;
import com.stefan.content.service.ContentService;
import com.stefan.pojo.TbContent;

/**
 * 内容管理Controller
 * @author 92377
 *
 */
@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value="/content/save", method=RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(TbContent content) {
		contentService.addContent(content);
		return E3Result.ok();
	}
	
}
