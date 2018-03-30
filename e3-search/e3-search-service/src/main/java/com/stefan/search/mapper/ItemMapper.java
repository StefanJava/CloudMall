package com.stefan.search.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stefan.common.pojo.SearchItem;

@Repository
public interface ItemMapper {
	
	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
	
}
