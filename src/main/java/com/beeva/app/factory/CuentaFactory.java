	package com.beeva.app.factory;

import com.beeva.app.impl.CuentaAhorrosImpl;
import com.beeva.app.impl.CuentaChequesImpl;
import com.beeva.app.inter.CuentaInterface;
import com.beeva.app.model.Account;



public class CuentaFactory {
	
	public CuentaFactory()
	{
		
	}
	
	public CuentaInterface getImplement(Account c)
	{
		if(c.getIdtipocuenta() == 1)
		{
			return new CuentaAhorrosImpl();
		}else if(c.getIdtipocuenta() == 2)
		{
			 return new CuentaChequesImpl();
		}
		
		return null;
	}
}
