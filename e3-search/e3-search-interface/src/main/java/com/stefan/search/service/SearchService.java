package com.stefan.search.service;

import com.stefan.common.pojo.SearchResult;

public interface SearchService {
	
	SearchResult search(String keyword, int page, int rows) throws Exception;
	
}
