package com.example.demo.service;

import java.util.List;

import com.example.demo.entitybean.UserInfo;

public interface UserService {	
	
	public void saveUser(UserInfo user);
	
	public void deleteUser(int id);
	
	public List<Object> getDepartmentCount();

	public List<UserInfo> getFilterData(UserInfo userInfo);
	
}
