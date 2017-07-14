package com.beeva.app.dao;


import java.util.List;

import com.beeva.app.model.User;

abstract public class UserDAO 
{
	
	abstract public boolean set(User user);
	abstract public User getById(int i);
	//public abstract void getAllClients();
	public abstract boolean updUser(User user);
}
