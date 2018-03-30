package com.stefan.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stefan.common.jedis.JedisClient;
import com.stefan.common.utils.E3Result;
import com.stefan.common.utils.JsonUtils;
import com.stefan.pojo.TbUser;
import com.stefan.sso.service.TokenService;

/**
 * 根据token查询用户信息
 * @author 92377
 *
 */
@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	@Override
	public E3Result getUserByToken(String token) {
		//根据token到redis中取用户信息
		String json = jedisClient.get("SESSION:" + token);
		if(StringUtils.isBlank(json)) {
			//取不到用户信息,登录已经过期,返回登录过期
			return E3Result.build(201, "用户登录已经过期");
		}
		//取到用户信息,更新token的过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		//返回结果
		return E3Result.ok(user);
	}

}
