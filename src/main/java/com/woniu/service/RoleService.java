package com.woniu.service;

import java.util.List;
import java.util.Map;

import com.woniu.pojo.PageInfo;
import com.woniu.pojo.Role;

public interface RoleService {
	Role login(Role role);

	List<Role> findAll();

	PageInfo<Role> selectByPage(PageInfo<Role> pageInfo);
	
	int countAll(PageInfo<Role> pageInfo);

	void save(Role role);

	Role findOne(Integer roleid);

	void update(Role role);

	void delete(Integer rid);

	void deleteRoles(Map<String, Object> map);

	List<Role> findAssignedRole(Integer user_id);

	List<Role> findUnassignedRole(Integer user_id);

	void assignPermission(Map<String, Object> map);
}
