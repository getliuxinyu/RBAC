package com.woniu.dao;

import java.util.List;
import java.util.Map;

import com.woniu.pojo.PageInfo;
import com.woniu.pojo.Role;

public interface RoleDao {
	void save(Role role);
	List<Role> selectByPage(PageInfo<Role> pageInfo);
	int countAll(PageInfo<Role> pageInfo);
	List<Role> findAssignedRole(Integer user_id);
	List<Role> findUnassignedRole(Integer user_id);
	Role findOne(Integer roleid);
	void update(Role role);
	void delete(Integer rid);
	void deleteRoles(Map<String, Object> map);
	void assignPermission(Map<String, Object> map);
}
