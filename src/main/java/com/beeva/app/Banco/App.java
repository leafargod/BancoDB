package com.beeva.app.Banco;

import java.util.List;
import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.app.dao.AccountDAO;
import com.beeva.app.dao.AccountTypeDAO;
import com.beeva.app.dao.BankDAO;
import com.beeva.app.dao.UserDAO;
import com.beeva.app.factory.CuentaFactory;
import com.beeva.app.impl.AccountImpl;
import com.beeva.app.impl.AccountTypeImpl;
import com.beeva.app.impl.BankImpl;
import com.beeva.app.impl.UserImpl;
import com.beeva.app.inter.CuentaInterface;
import com.beeva.app.model.Account;
import com.beeva.app.model.AccountType;
import com.beeva.app.model.Bank;
import com.beeva.app.model.User;
import com.beeva.app.model.Drools;

/**
 * Programa que maneja cuentas bancarias
 *
 */
public class App 
{
	public static void main( String[] args )
    {    		
			int resp;
	    	boolean bandMain = true, band = true, band3 = true;
	    	User user;
	    	Account acc;
	    	UserDAO userDao;
	    	AccountDAO accDao;
	    	Scanner in = new Scanner(System.in);
	    	ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
	    	
	    	
	    	System.out.println("/*Programa gestor de cuentas bancarias*/");
	    	System.out.println("/*Bienvenido al sistema */  \n");
	    	do
	    	{
	    		
		    	System.out.println("/*Menu*/"
		    			+ "\n1)Insertar Cliente"
		    			+ "\n2)Insertar Cuenta"
		    			+ "\n3)Usar Cuenta"
		    			+ "\n4)Ver Cuentas"
		    			+ "\n5)Inserta Catalogo Tipo Cuentas"
		    			+ "\n6)Inserta Catalogo Banco"
		    			+ "\n7)Salir"
		    			+ "\n8)Ayuda");
		    	resp = in.nextInt();
		    	switch(resp)
		    	{
		    		case 1:
		    			user = new User();
		    			boolean bandClient = true;
		    			
		    			do
		    			{
		    			System.out.println("/*Insertar Cliente*/");
		    			in.nextLine();
		    			System.out.println("Introduce el nombre: ");
		    			String firstName = in.nextLine();	
		    			System.out.println("Introduce los apellidos: ");
		    			String lastName = in.nextLine();
		    			System.out.println("¿Estan correctos los siguientes datos? y/* \n "
		    					+ "Nombre: "+ firstName + " Apellidos: "+ lastName);
		    			if(in.next().charAt(0) == 'y')
		    			{
		    				//INSERTA PROPIEDADES AL OBJETO
		    				user.setApellido(lastName);
		        	    	user.setNombre(firstName);
		        	    	//INSERTA EL OBJETO A LA BD
		        	    	
		        	    	userDao = (UserDAO)context.getBean(UserImpl.class);
		        	    	if(userDao.set(user))
		        	    		System.out.println("Cliente creado exitosamente!!!");
		        	    	else
		        	    		System.out.println("Hubo un error al crear el cliente, intente nuevamente más tarde!!!");
		        	    	bandClient = false;
		    			}
		    			}while(bandClient);
		    	    	
		    	    	
		    			
		    			break;
		    		case 2:
		    			int clientID, opc, accountType = 0;
		    			double balance;
		    			String accTypeName = "";
		    			band = true;
		    			band3 = true;
		    			acc = new Account();
		    			user = new User();
		    			do
		    			{
		    			System.out.println("/*Insertar Cuenta*/");
		    			in.nextLine();
		    			System.out.println("Introduce el id del cliente: ");
		    			clientID = in.nextInt();
		    			System.out.println("Introduce el balance inicial: ");
		    			balance = in.nextDouble();	
		    			do
		    			{
		    				System.out.println("Introduce el tipo de cuenta: \n1)CuentaAhorros \n2)CuentaCheques");
		    				opc = in.nextInt();
			    			if(opc == 1)
			    			{
			    				accountType = opc;
			    				accTypeName = "CuentaAhorros";
			    				band3 = false;
			    			}else if(opc == 2)
			    			{
			    				accountType = opc;
			    				accTypeName = "CuentaCheques";
			    				band3 = false;
			    			}else
			    			{
			    				System.out.println("!Opcion incorrecta, intenta nuevamente!");
			    			}
		    			}while(band3);
		    	    	userDao = (UserDAO) context.getBean(UserImpl.class);
		    	    	user = userDao.getById(clientID);
		    			System.out.println("¿Estan correctos los siguientes datos? y/* \n "
		    					+ "Cliente Nombre: "+ user.getNombre() + " Apellidos: "+ user.getApellido() + "\n "
		    							+ "Cuenta Balance inicial: " + balance + " TipoCuenta: "+ accTypeName);
		    			if(in.next().charAt(0) == 'y')
		    			{
		    				//INSERTA PROPIEDADES AL OBJETO
		    				acc.setBalance(balance);
		        	    	acc.setIdtipocuenta(accountType);
		        	    	acc.setIdcliente(clientID);
		        	    	//INSERTA EL OBJETO A LA BD
		        	    	accDao = (AccountDAO) context.getBean(AccountImpl.class);
		        	    	if(accDao.setAcc(acc))
		        	    		System.out.println("Cuenta creada exitosamente!!!");
		        	    	else
		        	    		System.out.println("Hubo un error al crear la cuenta, intente nuevamente más tarde!!!");
		        	    	band = false;
		    			}
		    			}while(band);
		    			break;
		    		case 3:
		    			band = true;
		    			band3 = true;
		    			acc = new Account();
		    			user = new User();
		    			double deposit = 0,saldo = 0;
		    			do
		    			{
			    			System.out.println("Ingresa el ID de la cuenta a usar");
			    			int accID = in.nextInt();
			    			acc = new Account();
		        	    	accDao = (AccountDAO) context.getBean(AccountImpl.class);
		        	    	userDao = (UserDAO) context.getBean(UserImpl.class);
		        	    	acc = (Account) accDao.getAccById(accID);
		        	    	user = (User) userDao.getById(acc.getIdcliente());
		        	    	System.out.println("¿Seguro que desea usar esta cuenta? y/*");
		        	    	System.out.println("Cliente Nombre :" + user.getNombre()
		        	    			+ "\nApellidos: " + user.getApellido());
		        	    	System.out.println("Cuenta Balance: "+ acc.getBalance()
		        	    			+ "\nTipo de cuenta: " + ((acc.getIdtipocuenta() == 1)?"CuentaAhorros":"CuentaCheques"));
		        	    	if(in.next().charAt(0) == 'y')
		        	    		band = false;
		    			}while(band);
		    			CuentaFactory accFact = new CuentaFactory();
		    			
	        	    	System.out.println("¿Que desea hacer?"
	        	    			+ "\n1)Depositar"
	        	    			+ "\n2)Retirar"
	        	    			+ "\n*)Regresar");
	        	    	switch(in.nextInt())
	        	    	{
		        	    	case 1:
		        	    			do
		        	    			{
		        	    				band = true;
			        	    			System.out.println("Introduce la cantidad que deseas depositar");
			        	    			deposit = in.nextDouble();
			        	    			if(deposit <= 0)
			        	    			{
			        	    				System.out.println("Cantidad invalida");
			        	    			}else
			        	    			{
			        	    				band = false;
			        	    			}
		        	    			}while(band);
		        	    			saldo = acc.getBalance();
		        	    			accFact.getImplement(acc).deposito(acc,deposit);
		        	    			try
		        	    	        {
		        	    	        	KieServices ks = KieServices.Factory.get();
		        	    	            KieContainer kContainer = ks.getKieClasspathContainer();
		        	    	            KieSession kSession = kContainer.newKieSession("ksession-rule");   
		        	    	            org.kie.api.runtime.rule.FactHandle factl;
		        	    	            Drools dro = new Drools(deposit,saldo,((acc.getIdtipocuenta() == 1)?"CuentaAhorros":"CuentaCheques"));
		        	    	            factl = kSession.insert(dro);
		        	    	            if(kSession.fireAllRules() == 0)
		        	    	            {
		        	    	            
		        	    	            	System.out.println("Deposito listo!");
		        	    	            }
		        	    	        }catch(Throwable t){
		        	    	        	System.out.println(t);
		        	    	        }
		        	    			
		        	    		break;
		        	    	case 2:
		        	    		double withdraw = 0;
		        	    			do
		        	    			{
		        	    				band=true;
				        	    		System.out.println("Introduce la cantidad que deseas retirar");
				        	    		withdraw = in.nextDouble();
			        	    			if(withdraw <= 0)
			        	    			{
			        	    				System.out.println("Cantidad invalida");
			        	    			}else
			        	    			{
			        	    				band = false;
			        	    			}
		        	    			}while(band);
		        	    			accFact.getImplement(acc).retiro(acc,withdraw);
		        	    		break;
		        	    	default:
	        	    	}
		    			break;
		    		case 4:
		    			System.out.println("Ingresa el ID de la cuenta a verificar");
		    			int accID = in.nextInt();
		    			acc = new Account();
	        	    	accDao = (AccountDAO) context.getBean(AccountImpl.class);
	        	    	userDao = (UserDAO) context.getBean(UserImpl.class);
	        	    	acc = (Account) accDao.getAccById(accID);
	        	    	user = (User) userDao.getById(acc.getIdcliente());
	        	    	System.out.println("Cliente Nombre :" + user.getNombre()
	        	    			+ "\nApellidos: " + user.getApellido());
	        	    	System.out.println("Cuenta Balance: "+ acc.getBalance()
	        	    			+ "\nTipo de cuenta: " + ((acc.getIdtipocuenta() == 1)?"CuentaAhorros":"CuentaCheques"));
		    			break;
		    		case 5:
		    			AccountTypeDAO accTypeDao = (AccountTypeDAO) context.getBean(AccountTypeImpl.class);
		    			AccountType accType = new AccountType();
		    			accType.setNombre("CuentaAhorros");
		    			accTypeDao.setAccType(accType);
		    			AccountType accType2 = new AccountType();
		    			accType2.setNombre("CuentaCheques");
		    			accTypeDao.setAccType(accType2);
		    			System.out.println("Cuentas creadas correctamente!!");
		    			break;
		    		case 6:
		    			BankDAO bankDao = (BankDAO) context.getBean(BankImpl.class);
		    			Bank bank = new Bank();
		    			bank.setNombre("Bancomer");
		    			bankDao.setBank(bank);
		    			Bank bank2 = new Bank();
		    			bank2.setNombre("Banamex");
		    			bankDao.setBank(bank2);
		    			System.out.println("Bancos creados correctamente!!");
		    			break;
		    		case 7:
		    			System.out.println("Saliendo del programa....");
		    			bandMain = false;
		    			break;
		    		case 8:
		    			System.out.println("y/* : y = aceptar , * = Cancelar con cualquier tecla \n");
		    			System.out.println("Insertar los catalogos primero antes de realizar cualquier otra accion  \n");
		    			System.out.println("Asegurarse de crear clientes y cuentas antes de usar o ver cuentas \n");
		    			break;
		    		default:
		    			System.out.println("Opción incorrecta, intente nuevamente");
		    			
		    	}
	    	}while(bandMain);
	    }
    	
    	
    }
	
