package com.beeva.app.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.app.dao.AccountDAO;
import com.beeva.app.inter.CuentaInterface;
import com.beeva.app.model.Account;
import com.beeva.app.model.User;
import com.mongodb.util.JSON;

/**
 *
 * @author administradorcito
 */
@Repository
public class CuentaAhorrosImpl implements CuentaInterface{
	@PersistenceContext
    EntityManager em;
    @Transactional
    public boolean retiro(Account c,double x)
    {
    	double newBalance = c.getBalance() - x;
    	
        if(c.getBalance() >= 5000)
        {
        	c.setBalance(newBalance);
        	ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
        	AccountDAO acDao = (AccountDAO)context.getBean(AccountImpl.class);
        	if(acDao.updAcc(c))
        	{
        		System.out.println("El retiro se ha completado exitosamente"); 
        	}else
        	{
        		System.out.println("Hubo un error al retirar la cantidad solicitada intente nuevamente");
        	}
        }else
        {
            //balance -= x;
        	System.out.println("El retiro debe ser igual o mayor que 5000");
        }
        
        return true;
        
    }
    

    public boolean deposito(Account c, double x)
    {
    	double newBalance = c.getBalance() + x;
    	c.setBalance(newBalance);
    	ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
    	AccountDAO acDao = (AccountDAO)context.getBean(AccountImpl.class);
    	if(acDao.updAcc(c))
    	{
    		System.out.println("El deposito se ha completado exitosamente"); 
    	}else
    	{
    		System.out.println("Hubo un error al depositar la cantidad solicitada intente nuevamente");
    	}
        return true;
        
    }


    
}
