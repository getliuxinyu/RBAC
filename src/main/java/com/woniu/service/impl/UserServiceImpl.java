package com.woniu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.UserDao;
import com.woniu.pojo.PageInfo;
import com.woniu.pojo.User;
import com.woniu.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public PageInfo<User> selectByPage(PageInfo<User> pageInfo) {
		// TODO Auto-generated method stub
		List<User> list = userDao.selectByPage(pageInfo);
		pageInfo.setList(list);
		int countAll = userDao.countAll(pageInfo);
		pageInfo.setTotalCount(countAll);
		int totalPage = pageInfo.getTotalCount()%pageInfo.getPageSize()==0?pageInfo.getTotalCount()/pageInfo.getPageSize():pageInfo.getTotalCount()/pageInfo.getPageSize()+1;
		pageInfo.setTotalPage(totalPage);
		return pageInfo;
	}

	@Override
	public int countAll(PageInfo<User> pageInfo) {
		// TODO Auto-generated method stub
		return userDao.countAll(pageInfo);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public User findOne(Integer user_id) {
		// TODO Auto-generated method stub
		return userDao.findOne(user_id);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void delete(Integer uid) {
		// TODO Auto-generated method stub
		userDao.delete(uid);
	}

	@Override
	public void deleteUsers(Integer[] uid) {
		// TODO Auto-generated method stub
		userDao.deleteUsers(uid);
	}

	@Override
	public void assignRole(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.assignRole(map);
	}

	@Override
	public void cancelRole(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.cancelRole(map);
	}
}
