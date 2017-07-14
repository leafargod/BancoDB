package com.beeva.app.impl;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.app.dao.UserDAO;
import com.beeva.app.model.Log;
import com.beeva.app.model.User;
@Repository
public class UserImpl extends UserDAO {
	@PersistenceContext
	EntityManager  em;
	@Override
	@Transactional
	public boolean set(User user) {
		// TODO Auto-generated method stub
		em.persist(user);
		Log l = new Log("testdb","log");
		l.insertLogCliente(user);
		return true;
		//n.insertLog("Cuenta",em.getProperties());
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		User us = em.find(User.class,id);
		return us;
	}

	@Override
	@Transactional
	public boolean updUser(User user) {
		em.merge(user);
		return true;
	}

	/*@Override
	@Transactional
	public void getAllClients() {
		TypedQuery<User> query =
			      em.createQuery("SELECT c FROM cliente c", User.class);
			  List<User> results = query.getResultList();
			  for (User c : results) {
		  	      System.out.println(c.getNombre());
		  	  }  
		
		Query q = em.createNativeQuery("SELECT c FROM bancodb.cliente c");
		List result = q.getResultList();
		for (User c : result) {
	  	      System.out.println(c.getNombre());
	  	  } */
		/*ListIterator<String> it = results.listIterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		
		return results;
	}*/
	
	/*public List<User> getNameStartsWith(String namePrefix)
	{
		String queryString="SELECT u FROM client WHERE u.name like :prefix" + "order by u.name";
		Query query = em.createQuery(queryString);
		query.setParameter("prefix",namePrefix + "%");
		return query.getResultList();
	}*/
	
	

}
