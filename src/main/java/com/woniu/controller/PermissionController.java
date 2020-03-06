package com.woniu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.Permission;
import com.woniu.pojo.User;
import com.woniu.service.PermissionService;

@Controller
@RequestMapping("permission")
public class PermissionController {
	@Autowired
	PermissionService permissionService;
	@RequestMapping("index")
	public String index() {
		return "permission/index";
	}
	
	@RequestMapping("save")
	public String save(Integer pid,Model model) {
		model.addAttribute("pid", pid);
		return "permission/save";
	}
	
	@RequestMapping("update")
	public String update(Integer pid,Model model) {
		Permission permission = permissionService.findOne(pid);
		model.addAttribute("pid", pid);
		model.addAttribute("permission",permission);
		return "permission/update";
	}
	
	@ResponseBody
	@RequestMapping("savePermission")
	public Object savePermission(Permission permission) {
		Message<Permission> message = new Message<>();
		try {
			permissionService.save(permission);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping("updatePermission")
	public Object updatePermission(Permission permission) {
		Message<Permission> message = new Message<>();
		try {
			permissionService.update(permission);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public Object delete(Integer pid) {
		Message<Permission> message = new Message<>();
		try {
			permissionService.delete(pid);
			message.setFlag(true);
		} catch(Exception e){
			message.setFlag(false);
		}
		return message;
	}
	
	
	
	@RequestMapping("loadData")
	@ResponseBody
	public Object loadData(Integer rid) {
		
		
		Permission root = null;
		List<Permission> list = permissionService.findAll();
		List<Permission> zTree = new ArrayList<Permission>();
		
		//先将所有节点放入到map中
		Map<Integer, Permission> map = new HashMap<Integer, Permission>();
		for (Permission permission : list) {
			//节点id和对象
			map.put(permission.getPid(), permission);
		}
		
		//循环所有得集合
		for (Permission permission : list) {
			permission.setOpen(true);
			//如果等于null,说明该节点是根节点。将根节点加入加入集合
			if(permission.getParentid()==null) {
				zTree.add(permission);
			}else {
				//else不是根节点，通过map集合找到该子节点的父节点
				Permission p = map.get(permission.getParentid());
				//将子节点加入父节点
				p.getChildren().add(permission);
			}
		}
		
//		for (Permission child : list) {
//			//判断是否为根节点
//			if(child.getParentid()==null) {
//				zTree.add(child);
//			}else {
//				//循环所有集合将子节点加入到根节点
//				for (Permission permission : list) {
//					if(child.getParentid()==permission.getPid()) {
//						permission.getChildren().add(child);
//					}
//				}
//			}
//		}
		
		//递归添加
//		Permission root1 = permissionService.findRoot();
//		queryZTree(root1);
//		List<Permission> zTree = new ArrayList<Permission>();
//		zTree.add(root1);
		//递归添加
		
		return zTree;

	}
	
	
	
	
	
	public void queryZTree(Permission permission) {
		//根据根节点查找子节点
		List<Permission> children = permissionService.selectByParentid(permission.getParentid());
		//循环遍历
		if(children!=null) {
			for (Permission child : children) {
				queryZTree(child);
			}
		}
		//添加到根节点中
		permission.setChildren(children);
	}
}


