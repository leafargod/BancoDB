package com.beeva.app.dao;

import org.springframework.stereotype.Repository;

import com.beeva.app.model.Account;
import com.beeva.app.model.Bank;

public abstract class BankDAO 
{
	public abstract void setBank(Bank bank);
	public abstract Bank getBankById(int in);
	public abstract boolean updBank(Bank bank);
}
