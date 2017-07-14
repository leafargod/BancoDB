package com.beeva.app.dao;

import com.beeva.app.model.Account;
public abstract class AccountDAO {
	
	public abstract boolean setAcc(Account ac);
	public abstract Account getAccById(int id);
	public abstract boolean updAcc(Account ac);

}
