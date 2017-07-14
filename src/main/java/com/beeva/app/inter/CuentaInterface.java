package com.beeva.app.inter;
import com.beeva.app.model.Account;

public interface CuentaInterface 
{
	
	public boolean deposito(Account c,double x);
	public boolean retiro(Account c,double x);

}
