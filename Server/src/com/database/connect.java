package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//This method can let the server connect to the Database 
public class connect {
	
	private Connection conn;
	public void connectDatabase()
	{
	    String DATABASE = "Dussenger";
	    String USER = "admin";
	    String PASSWORD = "ECE651";
	        try { 
	            Class.forName("org.postgresql.Driver"); 
	            String url = "jdbc:postgresql://localhost/" + DATABASE + "?user=" + USER + "&password=" + PASSWORD; 
	            this.conn = DriverManager.getConnection(url);
	            conn.close();
	            
	            } 
	            catch (ClassNotFoundException e) { 
	              e.printStackTrace(); 
	            }
	            catch (SQLException e) { 
	                e.printStackTrace(); 
	            }	
	}
}
