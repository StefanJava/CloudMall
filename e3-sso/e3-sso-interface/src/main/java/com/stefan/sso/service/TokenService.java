package com.stefan.sso.service;

import com.stefan.common.utils.E3Result;

public interface TokenService {
	
	E3Result getUserByToken(String token);
	
}
