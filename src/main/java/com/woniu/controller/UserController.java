package com.woniu.controller;





import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.PageInfo;
import com.woniu.pojo.Role;
import com.woniu.pojo.User;
import com.woniu.service.RoleService;
import com.woniu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@ResponseBody
	@RequestMapping("/userByPage")//分页
	public Object userByPage(PageInfo<User> pageInfo) {
		Message<User> message = new Message<User>();
		try {
			if(pageInfo.getCurrentPage()==null) {
				pageInfo.setCurrentPage(1);
			}
			pageInfo.setPageSize(5);
			pageInfo = userService.selectByPage(pageInfo);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		message.setPageInfo(pageInfo);
		return message;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/userSave")//保存
	public Object userSave(User user) {
		Message<User> message = new Message<User>();
		try {
			user.setUser_pwd("123");
			user.setUser_date(new Date());
			userService.save(user);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		message.setT(user);
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/userUpd")//修改
	public Object userUpd(User user) {
		Message<User> message = new Message<User>();
		try {
			userService.update(user);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		message.setT(user);
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/deleteUsers") //批量删除
	public Object usersDel(Integer[] uids) {
		Message<User> message = new Message<User>();
		try {
		
			userService.deleteUsers(uids);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/deleteUser") //删除一个
	public Object userDel(Integer uid) {
		Message<User> message = new Message<User>();
		try {
			userService.delete(uid);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		return message;
	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/assignRoleDo") //分配角色
	public Object assignRoleDo(Integer uid,Integer[] rightRids) {
		Message<User> message = new Message<User>();
		
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("uid", uid);
			map.put("rightRids", rightRids);
			userService.assignRole(map);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		
		return message;
	}
	
	
	@ResponseBody
	@RequestMapping("/cancelRole") //分配角色
	public Object cancelRoleDo(Integer uid,Integer[] leftRids) {
		Message<User> message = new Message<User>();
		
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("uid", uid);
			map.put("leftRids", leftRids);
			userService.cancelRole(map);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		
		return message;
	}
	
	@RequestMapping("/userJsp")
	public String userJsp(HttpServletRequest req,Model model,PageInfo<User> pageInfo) {
		if(pageInfo.getCurrentPage()==null) {
			pageInfo.setCurrentPage(1);
		}
		pageInfo.setPageSize(3);
		pageInfo = userService.selectByPage(pageInfo);
		System.out.println(pageInfo.getTotalPage());
		model.addAttribute("pageInfo", pageInfo);
		return "user/user";
	}
	
	//转发到user展示页面
	@RequestMapping("/user")
	public String user() {
		return "user/user";
	}
	
	//转发到增加用户界面
	@RequestMapping("/userAdd")
	public String userAdd() {
		return "user/userAdd";
	}
	
	
	@RequestMapping("/userUpdForm") //修改页面
	public String userUpd(Integer user_id,Model model) {
		User user = userService.findOne(user_id);
		model.addAttribute("user", user);
		return "user/userUpd";
	}
	
	@RequestMapping("/assignRole") //角色页面
	public String assignRole(Integer user_id,Model model) {
		List<Role> assList = roleService.findAssignedRole(user_id);
		List<Role> unassList = roleService.findUnassignedRole(user_id);
		model.addAttribute("assList", assList);
		model.addAttribute("unassList", unassList);
		model.addAttribute("uid", user_id);
		return "user/assignRole";
	}
}
