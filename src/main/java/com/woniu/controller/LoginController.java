package com.woniu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
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
	
	@RequestMapping("loginout")
	public String loginout(HttpSession session) {
		//退出将session中存储的信息移除
		session.removeAttribute("loginUser");
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
			message.setFlag(true);
			
		}else {
			message.setFlag(false);
			
		}
		
		return message;
	}
}
