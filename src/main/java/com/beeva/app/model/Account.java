package com.beeva.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="cuenta")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcuenta;
	private int idcliente;
	private double balance;
	private int idtipocuenta;
	public int getIdcuenta() {
		return idcuenta;
	}
	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getIdtipocuenta() {
		return idtipocuenta;
	}
	public void setIdtipocuenta(int idtipocuenta) {
		this.idtipocuenta = idtipocuenta;
	}
	
	
	
	
	}
	
