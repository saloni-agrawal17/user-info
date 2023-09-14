package com.example.demo.dao;

import java.util.List;

import com.example.demo.entitybean.UserInfo;

public interface UserDao {

	public void saveUser(UserInfo user);
	
	public void deleteUser(int id);

	public List<Object> getDepartmentCount();

	public List<UserInfo> getFilterData(UserInfo userInfo);
	
}
