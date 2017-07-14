package com.beeva.app.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Log {

	private ApplicationContext context;
	private MongoClient mongo;
	private DB db;
	private String collection;
	private String dbName;
	private Calendar cal = Calendar.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public Log(String dbName, String collection)
	{
		this.context = new ClassPathXmlApplicationContext("MongoSpring.xml");
		this.mongo = (MongoClient) context.getBean("mongoSource");
		this.db = mongo.getDB(dbName);
		this.collection = collection;
		this.dbName = dbName;
	}
	
	public void insertLogCuenta(Account ac)
	{
		DBCollection table = db.getCollection(collection);
		BasicDBObject document = new BasicDBObject();
		document.put("mensaje:","Se inserto una cuenta nueva");
		document.put("id",ac.getIdcuenta());
		document.put("balance",ac.getBalance());
		document.put("tipocuenta",ac.getIdtipocuenta());
		document.put("clienteid",ac.getIdcliente());
		document.put("fecha",dateFormat.format(cal.getTime()));
		
		table.insert(document);
	}
	
	public void insertLogCliente(User ac)
	{
		DBCollection table = db.getCollection(collection);
		BasicDBObject document = new BasicDBObject();
		document.put("mensaje:","Se inserto un cliente nuevo");
		document.put("id",ac.getIdcliente());
		document.put("nombre",ac.getNombre());
		document.put("apellido",ac.getApellido());
		document.put("fecha",dateFormat.format(cal.getTime()));
		table.insert(document);
	}
	
	public void insertLogBanco(Bank ac)
	{
		DBCollection table = db.getCollection(collection);
		BasicDBObject document = new BasicDBObject();
		document.put("mensaje:","Se inserto una cuenta nueva");
		document.put("id",ac.getIdbanco());
		document.put("nombre",ac.getNombre());
		document.put("fecha",dateFormat.format(cal.getTime()));
		table.insert(document);
	}

}
