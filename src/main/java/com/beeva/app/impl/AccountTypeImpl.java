package com.beeva.app.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.app.dao.AccountTypeDAO;
import com.beeva.app.model.AccountType;
@Repository
public class AccountTypeImpl extends AccountTypeDAO{
	@PersistenceContext
	EntityManager em;
	@Override
	@Transactional
	public void setAccType(AccountType act) {
		// TODO Auto-generated method stub
		em.persist(act);
		
	}

	@Override
	public AccountType getAccType(int id) {
		// TODO Auto-generated method stub
		return em.find(AccountType.class, id);
	}

}
