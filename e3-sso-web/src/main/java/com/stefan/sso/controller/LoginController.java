package com.stefan.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stefan.common.utils.CookieUtils;
import com.stefan.common.utils.E3Result;
import com.stefan.sso.service.LoginService;

/**
 * 用户登陆处理Controller
 * @author 92377
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@RequestMapping("/page/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public E3Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		E3Result result = loginService.userLogin(username, password);
		//判断是否登录成功,如果登录成功把token写入cookie
		if(result.getStatus() == 200) {
			String token = result.getData().toString();
			CookieUtils.setCookie(request, response, TOKEN_KEY, token);
		}
		//返回结果
		return result;
	}
	
}
