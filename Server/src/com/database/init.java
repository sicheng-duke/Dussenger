package com.database;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/*initialize Database, create table to store user information
* and relationship between users
*/ 
public class init {

	private Connection conn;
	public void initDatabase()
	{
	    String DATABASE = "Dussenger";
	    String USER = "admin";
	    String PASSWORD = "ECE651";
	        try { 
	            Class.forName("org.postgresql.Driver"); 
	            String url = "jdbc:postgresql://localhost/" + DATABASE + "?user=" + USER + "&password=" + PASSWORD; 
	            this.conn = DriverManager.getConnection(url);
	            
	            Statement stmt = null;
	    		stmt = conn.createStatement();
	            String user_info = "CREATE TABLE USERINFO " +
	                         "(USERNAME    VARCHAR PRIMARY KEY     NOT NULL," +
	                         " PASSWORD    VARCHAR                 NOT NULL   , " +
	                         " AGE         INT   , " +
	                         " ID          INT);";
	            stmt.executeUpdate(user_info);
	            
	            
	            
	            String relation = "CREATE TABLE RELATION " +
	                         "(USERNAME VARCHAR  NOT NULL," +
	                         " FRIEND   VARCHAR   NOT NULL,"+
	                         " PRIMARY KEY(USERNAME, FRIEND)); ";
	            stmt.executeUpdate(relation);

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
