package com.woniu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.PageInfo;
import com.woniu.pojo.Permission;
import com.woniu.pojo.Role;
import com.woniu.service.PermissionService;
import com.woniu.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	RoleService roleService;
	@Autowired
	PermissionService permissionService;
	
	
	@RequestMapping("role")
	public String role() {
		return "role/role";
	}
	
	
	@RequestMapping("roleUpdForm")
	public String roleUpdForm(Integer rid,Model model) {
		Role role = roleService.findOne(rid);
		model.addAttribute("role", role);
		return "role/roleUpd";
	}
	
	@RequestMapping("save")
	public String save() {
		return "role/roleAdd";
	}
	
	@RequestMapping("loadData")
	@ResponseBody
	public Object assignPermission(Integer rid) {
		List<Permission> list = permissionService.findAll();
		List<Integer> assignPermissionIds=permissionService.assignedPermission(rid);
		List<Permission> zTree = new ArrayList<Permission>();
		Map<Integer, Permission> map = new HashMap<Integer, Permission>();
		for (Permission permission : list) {
			//找到已经分配的许可将checked属性设置为true
			if(assignPermissionIds.contains(permission.getPid())) {
				permission.setChecked(true);
			}else{
				permission.setChecked(false);
			}
			map.put(permission.getPid(), permission);
		}
		for (Permission permission : list) {
			if(permission.getParentid()==null) {
				zTree.add(permission);
			}else {
				Permission p = map.get(permission.getParentid());
				p.getChildren().add(permission);
			}
		}
		
		return zTree;
	}
	
	@RequestMapping("roleByPage")
	@ResponseBody
	public Object roleByPage(PageInfo<Role> pageInfo) {
		Message<Role> message = new Message<>();
		try {
			if(pageInfo.getCurrentPage()==null) {
				
				pageInfo.setCurrentPage(1);
			}
			pageInfo.setPageSize(4);
			pageInfo = roleService.selectByPage(pageInfo);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		message.setPageInfo(pageInfo);
		return message;
	}
	
	@RequestMapping("roleSave")
	@ResponseBody
	public Object roleAdd(Role role) {
		Message<Role> message = new Message<>();
		try {
			roleService.save(role);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		
		return message;
	}
	
	@RequestMapping("assignPermissionDo")
	@ResponseBody
	public Object assignPermissionDo(Integer rid,Integer[] pids) {
		Message<Role> message = new Message<>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rid", rid);
			map.put("pids", pids);
			roleService.assignPermission(map);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		
		return message;
	}
	
	@RequestMapping("roleUpd")
	@ResponseBody
	public Object roleUpd(Role role) {
		Message<Role> message = new Message<>();
		try {
			roleService.update(role);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		
		return message;
	}
	
	@RequestMapping("deleteRole")
	@ResponseBody
	public Object deleteRole(Integer rid) {
		Message<Role> message = new Message<>();
		try {
			roleService.delete(rid);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		
		return message;
	}
	
	//批量删除
	@RequestMapping("deleteRoles")
	@ResponseBody
	public Object deleteRoles(Integer[] rids) {
		Message<Role> message = new Message<>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rids", rids);
			roleService.deleteRoles(map);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		
		return message;
	}
}
