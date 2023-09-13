package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entitybean.UserInfo;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImplemantation implements UserService{

	private UserDao userDao;

	public UserServiceImplemantation(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void saveUser(UserInfo user) {
		
		userDao.saveUser(user);
		
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		
		userDao.deleteUser(id);
		
	}

	@Override
	@Transactional
	public List<Object> getDepartmentCount() {
		
		return userDao.getDepartmentCount();
		
	}

}
