package com.woniu.dao;

import java.util.List;

import com.woniu.pojo.Permission;

public interface PermissionDao {

	List<Permission> findAll();

	Permission findRoot();

	List<Permission> selectByParentid(Integer parentid);

	void save(Permission permission);

	Permission findOne(Integer pid);

	void update(Permission permission);

	void delete(Integer pid);

	List<Permission> findUnassignedPermission(Integer rid);

}
