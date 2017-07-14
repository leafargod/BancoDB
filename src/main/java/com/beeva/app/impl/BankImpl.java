package com.beeva.app.impl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.app.dao.BankDAO;
import com.beeva.app.model.Bank;
import com.beeva.app.model.Log;

@Repository
public class BankImpl extends BankDAO
{
	@PersistenceContext
	EntityManager em;
	@Override
	@Transactional
	public void setBank(Bank bank) 
	{
		// TODO Auto-generated method stub
		em.persist(bank);
		Log l = new Log("testdb","bancolog");
		l.insertLogBanco(bank);
	}
	@Override
	public Bank getBankById(int id) {
		return em.find(Bank.class, id);
	}
	@Override
	@Transactional
	public boolean updBank(Bank bank) {
		em.merge(bank);
		return true;
	}

	//@Override
	/*public Bank getBank(int in) 
	{
		return em.find(Bank.class,in);
		User user = users.getContent.get(0);
		return null;
	}
	
	public List<User> getUsersBank(int i)
	{
		
	}*/

}
