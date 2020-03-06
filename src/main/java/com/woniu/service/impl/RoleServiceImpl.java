package com.woniu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.RoleDao;
import com.woniu.pojo.PageInfo;
import com.woniu.pojo.Role;
import com.woniu.service.RoleService;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDao roleDao;
	
	@Override
	public Role login(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Role> selectByPage(PageInfo<Role> pageInfo) {
		int countAll = roleDao.countAll(pageInfo);
		pageInfo.setTotalCount(countAll);
		int totalPage = pageInfo.getTotalCount()%pageInfo.getPageSize()==0?pageInfo.getTotalCount()/pageInfo.getPageSize():pageInfo.getTotalCount()/pageInfo.getPageSize()+1;
		pageInfo.setTotalPage(totalPage);
		List<Role> list = roleDao.selectByPage(pageInfo);
		pageInfo.setList(list);
		return pageInfo;
	}

	@Override
	public int countAll(PageInfo<Role> pageInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	@Override
	public Role findOne(Integer roleid) {
		// TODO Auto-generated method stub
		return roleDao.findOne(roleid);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	@Override
	public void delete(Integer rid) {
		// TODO Auto-generated method stub
		roleDao.delete(rid);
	}

	@Override
	public void deleteRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		roleDao.deleteRoles(map);
	}

	@Override
	public List<Role> findAssignedRole(Integer user_id) {
		// TODO Auto-generated method stub
		
		return roleDao.findAssignedRole(user_id);
	}

	@Override
	public List<Role> findUnassignedRole(Integer user_id) {
		// TODO Auto-generated method stub
		
		return roleDao.findUnassignedRole(user_id);
	}

	@Override
	public void assignPermission(Map<String, Object> map) {
		// TODO Auto-generated method stub
		//分配权限同时删除原有权限
		roleDao.deleteAssignedPermission(map.get("rid"));
		roleDao.assignPermission(map);
	}

}
