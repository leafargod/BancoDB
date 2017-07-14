package com.beeva.app.impl;
import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.app.dao.AccountDAO;
import com.beeva.app.inter.CuentaInterface;
import com.beeva.app.model.Account;
/**
 *
 * @author administradorcito
 */
public class CuentaChequesImpl implements CuentaInterface
{
    
    public CuentaChequesImpl()
    {
    }
    public boolean retiro(Account c,double x)
    {
    	double newBalance = c.getBalance() - x;
    	Calendar cal = Calendar.getInstance();
    	int dayNow = cal.get(Calendar.DAY_OF_WEEK);
    	if(dayNow != 1 && dayNow != 7)
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
        	System.out.println("Solo se puede retirar los dias entre semana");
        	return false;
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
