package com.stefan.sso.service;

import com.stefan.common.utils.E3Result;
import com.stefan.pojo.TbUser;

public interface RegisterService {
	
	E3Result checkData(String param, int type);
	E3Result register(TbUser user);
}
