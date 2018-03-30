package com.stefan.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.stefan.cart.service.CartService;
import com.stefan.common.utils.CookieUtils;
import com.stefan.common.utils.E3Result;
import com.stefan.common.utils.JsonUtils;
import com.stefan.pojo.TbItem;
import com.stefan.pojo.TbUser;
import com.stefan.sso.service.TokenService;

/**
 * 用户登录拦截器
 * @author 92377
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Value("${SSO_URL}")
	private String SSO_URL;
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private CartService cartService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		//判断token是否存在
		if(StringUtils.isBlank(token)) {
			//不存在,未登录,跳转到sso系统的登录页面,登录之后再跳转回来
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			//拦截
			return false;
		}
		
		//存在,需要调用sso系统的服务,根据token取用户信息
		E3Result result = tokenService.getUserByToken(token);
		//取不到,登录过期,重新登录
		if(result.getStatus() != 200) {
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			//拦截
			return false;
		}
		//取到,把用户信息写入request
		TbUser user = (TbUser) result.getData();
		request.setAttribute("user", user);
		//判断cookie中是否有购物车数据,如果有就合并到服务端
		String jsonCartList = CookieUtils.getCookieValue(request, "cart", true);
		if(StringUtils.isNotBlank(jsonCartList)) {
			//合并购物车
			cartService.mergeCart(user.getId(), JsonUtils.jsonToList(jsonCartList, TbItem.class));
		}
		//放行
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
