package com.beeva.app.dao;
import org.springframework.stereotype.Repository;

import com.beeva.app.model.AccountType;
@Repository
public abstract class AccountTypeDAO 
{
	public abstract void setAccType(AccountType act);
	public abstract AccountType getAccType(int id);
}
