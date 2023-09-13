package com.example.demo.dao;


import java.util.List;

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
		
		List<Object> object = query.getResultList();
			
		return object;
	}
	
	
}
