package com.example.demo.dao;


import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.example.demo.entitybean.UserInfo;
import com.fasterxml.jackson.databind.util.JSONPObject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class UserDaoImplementation implements UserDao{

	private EntityManager entityManager;
	
	public UserDaoImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void saveUser(UserInfo user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(user);
		
	}

	@Override
	public void deleteUser(int id) {

		Session currentSession = entityManager.unwrap(Session.class);
		
		Query query = currentSession.createQuery("delete from UserInfo where id=:id");
		
		query.setParameter("id", id);
		
		query.executeUpdate();
	}

	@Override
	public List<Object> getDepartmentCount() {

		Session currentSession = entityManager.unwrap(Session.class);
		
		Query query = currentSession.createQuery("select e.department department,count(e.department) count from UserInfo e group by e.department");
		
		List<Object> result = query.getResultList();
			
		return result;
	}

	@Override
	public List<UserInfo> getFilterData(UserInfo userInfo) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String query = "FROM UserInfo u WHERE ";
		boolean isSingleWhereConditionPresent = false;
		
		if(userInfo.getFirstName() != null && !userInfo.getFirstName().equals("")) {
			query += "u.firstName = "+userInfo.getFirstName();
			
			isSingleWhereConditionPresent = true;
		}
		if(userInfo.getLastName() != null && !userInfo.getLastName().equals("")) {
			
			if(isSingleWhereConditionPresent) {
				query+=" AND ";
			}
			query += "u.lastName = "+userInfo.getLastName();
			
			isSingleWhereConditionPresent = true;
		}
		if(userInfo.getCity() != null && !userInfo.getCity().equals("")) {
			if(isSingleWhereConditionPresent) {
				query+=" AND ";
			}
			
			query += "u.city = "+userInfo.getCity();
			
			isSingleWhereConditionPresent = true;
		}
		if(userInfo.getDepartment() != null && !userInfo.getDepartment().equals("")) {
			if(isSingleWhereConditionPresent) {
				query+=" AND ";
			}
			
			query += "u.department = "+userInfo.getDepartment();
			
			isSingleWhereConditionPresent = true;
		}
		if(userInfo.getBankCurrency() != null && !userInfo.getBankCurrency().equals("")) {
			if(isSingleWhereConditionPresent) {
				query+=" AND ";
			}
			
			query += "u.bankCurrency = "+userInfo.getBankCurrency();
			
			isSingleWhereConditionPresent = true;
		}
		if(userInfo.getHeight() != 0f) {
			if(isSingleWhereConditionPresent) {
				query+=" AND ";
			}
			
			query += "u.height = "+userInfo.getHeight();
			
			isSingleWhereConditionPresent = true;
		}
		
		List<UserInfo> result = currentSession.createQuery(query, UserInfo.class).list();
		
		return result;
	}
	
	
}
	