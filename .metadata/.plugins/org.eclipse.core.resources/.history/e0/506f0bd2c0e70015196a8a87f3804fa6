package com.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;
public class relation {
	private Connection conn;
	private Statement stmt;
	public relation()
	{
		connect relconnect = new connect();
		relconnect.connectDatabase();
		List<String> print = null;
		conn = relconnect.getConn();
		try {
			print = getFriend("Bcc");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s: print)
		{
			System.out.println(s);
		}
	}
	
	
	public List<String>  getFriend(String s) throws Throwable
	{
		List<String> result = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT FRIEND FROM RELATION WHERE USERNAME = '"+ s + "';");
		while(rs.next())
		{
			result.add(rs.getString("FRIEND"));
		}
		return result;
	}

}
