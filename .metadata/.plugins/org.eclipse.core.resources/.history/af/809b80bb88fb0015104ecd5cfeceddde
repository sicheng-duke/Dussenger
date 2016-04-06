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
