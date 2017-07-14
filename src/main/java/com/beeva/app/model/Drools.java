package com.beeva.app.model;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Drools {
	
	private double saldo;
	private double deposito;
	private String accountType;
	
	public Drools(double deposito,double saldo,String accountType)
    {		
        
	        this.saldo = saldo;
	        this.deposito = deposito;
	        this.accountType = accountType;  
    }

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getDeposito() {
		return deposito;
	}

	public void setDeposito(double deposito) {
		this.deposito = deposito;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public boolean check()
	{
		
		return true;
	}

}
