package com.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import com.auth.*;

import java.util.*;
public class Relation {
	private   Connection conn;




	private Statement stmt;
	public Relation()
	{
		connect relconnect = new connect();
		relconnect.connectDatabase();
		conn = relconnect.getConn();

	}
	
	public void closeConn()
	{
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//true -> no relation
	//false ->has relation
	public boolean checkRelation(String s1, String s2) throws Throwable
	{
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM RELATION WHERE USERNAME = '"+ s1 + "' AND FRIEND = '"+s2+"';");
		while(rs.next())
		{
			return false;
		}
		return true;
		
	}

	public void deleteRelation(String s1,String s2) throws Throwable
	{
		stmt = conn.createStatement();
		stmt.execute("DELETE from RELATION where USERNAME ='"+s1+"' and FRIEND = '"+s2+"';");
		stmt.execute("DELETE from RELATION where USERNAME ='"+s2+"' and FRIEND = '"+s1+"';");
		
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
