package com.beeva.app.impl;

import java.util.Hashtable;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.app.dao.AccountDAO;
import com.beeva.app.model.Account;
import com.beeva.app.model.Log;
import com.beeva.app.model.User;
import com.beeva.app.model.Log;
@Repository
public class AccountImpl extends AccountDAO {
	@PersistenceContext
	EntityManager em;
	@Override
	@Transactional
	public boolean setAcc(Account ac) {
		// TODO Auto-generated method stub
		em.persist(ac);
		Log l = new Log("testdb","log");
		l.insertLogCuenta(ac);
		
		return true;
	}

	@Override
	public Account getAccById(int id) 
	{
		
		Account acc = em.find(Account.class,id);
		return acc;
		
	}

	@Override
	@Transactional
	public boolean updAcc(Account ac) {
		em.merge(ac);
		return true;
	}
	
	

}
