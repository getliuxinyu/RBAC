package com.woniu.dao;

import java.util.List;
import java.util.Map;

import com.woniu.pojo.PageInfo;
import com.woniu.pojo.User;

public interface UserDao {

	User login(User user);

	List<User> findAll();

	List<User> selectByPage(PageInfo<User> pageInfo);

	int countAll(PageInfo<User> pageInfo);

	void save(User user);

	User findOne(Integer user_id);

	void update(User user);

	void delete(Integer user_id);

	void deleteUsers(Integer[] uid);

	void assignRole(Map<String, Object> map);

	void cancelRole(Map<String, Object> map);

}
