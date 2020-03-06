package com.woniu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.woniu.pojo.User;
/**
 * 登录拦截器
 * @author very
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	/**
	 * 执行在浏览器访问controller之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//在用户访问controller时，需要先判断是否已登录。如果未登录则跳转到登录页面
		//获得session中的用户信息
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser!=null) {
			return true;
		}else {
			response.sendRedirect(request.getServletContext().getContextPath()+"/login");
		}
		//判断用户信息
		return false;
	}
	
	/**
	 * 执行在浏览器访问controller之后
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 执行在视图渲染之后
	 * 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
