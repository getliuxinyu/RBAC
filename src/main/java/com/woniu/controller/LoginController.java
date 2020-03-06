package com.woniu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.Permission;
import com.woniu.pojo.User;
import com.woniu.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@RequestMapping("loginJsp")
	public String loginJsp(User user,HttpSession session,Model model) {
		User loginUser = userService.login(user);
		if(loginUser!=null) {
			session.setAttribute("loginUser", loginUser);
			
			//登录菜单存入信息
			
			return "redirect:main";
		}
		model.addAttribute("message", "用户名密码错误！");
		//登录失败重定向的controller中的login
		return "redirect:login";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("error")
	public String error() {
		return "error";
	}
	
	@RequestMapping("loginout")
	public String loginout(HttpSession session) {
		//退出将session中存储的信息移除
		//session.removeAttribute("loginUser");
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("loginAjax")
	@ResponseBody
	public Message loginAjax(User user,HttpSession session) {
		Message message = new Message();
		User loginUser = userService.login(user);
		if(loginUser!=null) {
			session.setAttribute("loginUser", loginUser);
			
			//登录菜单存入信息
			/**
			 * 获得该用户的许可
			 * 1、users
			 */
			List<Permission> permissionLis=userService.selectPermissionByUser(loginUser);
			List<Permission> menuList = new ArrayList<Permission>();
			Permission root = null;
			Map<Integer, Permission> map = new HashMap<Integer, Permission>();
			for (Permission permission : permissionLis) {
				permission.setOpen(true);
				map.put(permission.getPid(), permission);
			}
			for (Permission permission : permissionLis) {
				if(permission.getParentid()==null) {
					root=permission;
				}else {
					Permission parent = map.get(permission.getParentid());
					parent.getChildren().add(permission);
				}
				
				
			}
			session.setAttribute("root", root);
			message.setFlag(true);
		}else {
			message.setFlag(false);
			
		}
		
		return message;
	}
}
