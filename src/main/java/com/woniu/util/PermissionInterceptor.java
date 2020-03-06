package com.woniu.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.woniu.pojo.Permission;
import com.woniu.pojo.User;
import com.woniu.service.PermissionService;
import com.woniu.service.UserService;
/**
 * 权限许可拦截器
 * @author very
 *
 */
public class PermissionInterceptor implements HandlerInterceptor {
	
	@Autowired
	PermissionService permissionService;
	@Autowired
	UserService userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//访问的信息正常
		//获得所有许可
		//获得请求信息
		String uri = request.getRequestURI();
		List<Permission> perList = permissionService.findAll();
		Boolean flag=false;
		for (Permission permission : perList) {
			if((request.getServletContext().getContextPath()+permission.getUrl()).equals(uri)) {
				flag=true;
				break;
			}
		}
		//访问的用户具备相应的权限
		if(flag) {
			//
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			//判断请求路径是否是该用户的权限
			List<Permission> userPermission = userService.selectPermissionByUser(loginUser);
			
			for (Permission permission : userPermission) {
				//判断请求的信息是当前用户所拥有的权限
				if((request.getServletContext().getContextPath()+permission.getUrl()).equals(uri)) {
					return true;
				}
			}
			String message = "1";
			response.sendRedirect(request.getServletContext().getContextPath()+"/error?message="+message);
		}else {
			//用户请求的路径是错误的
			String message = "2";
			response.sendRedirect(request.getServletContext().getContextPath()+"/error?message="+message);
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
