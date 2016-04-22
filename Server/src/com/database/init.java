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
	public init()
	{
		initDatabase();
		groupInit();
		
	}
	public void groupInit()
	{
		 String DATABASE = "Dussenger";
		 String USER = "admin";
		 String PASSWORD = "ECE651";
		 try { 
	            Class.forName("org.postgresql.Driver"); 
	            String url = "jdbc:postgresql://localhost/" + DATABASE + "?user=" + USER + "&password=" + PASSWORD; 
	            this.conn = DriverManager.getConnection(url);  //get a connection object
	            
	            Statement stmt = null;  
	    		String drop_group = "DROP TABLE IF EXISTS GPCHAT;";
	    		String drop_groupRec = "DROP TABLE IF EXISTS GPRECORD;";
	            stmt = conn.createStatement(); //get a statement object
	            stmt.executeUpdate(drop_group);
	            stmt.executeUpdate(drop_groupRec);
	            
	            String group = "CREATE TABLE GPCHAT " +
                        "(NAME VARCHAR  NOT NULL," +
                        " OWNER   VARCHAR   NOT NULL,"+
                        " MEMBER   VARCHAR   NOT NULL,"+
                        " PRIMARY KEY(NAME, OWNER)); ";
	            stmt.executeUpdate(group);
	            
	            String groupRec = "CREATE TABLE GPRECORD " +
                        "(USRNAME VARCHAR  NOT NULL," +
                        " GPNAME   VARCHAR   NOT NULL,"+
                        " PRIMARY KEY(USRNAME, GPNAME)); ";
	            stmt.executeUpdate(groupRec);
	            conn.close();
		 }
         catch (ClassNotFoundException e) { 
             e.printStackTrace(); 
           }
           catch (SQLException e) { 
               e.printStackTrace(); 
           }
	}
	public void initDatabase()
	{
	    String DATABASE = "Dussenger";
	    String USER = "admin";
	    String PASSWORD = "ECE651";
	        try { 
	            Class.forName("org.postgresql.Driver"); 
	            String url = "jdbc:postgresql://localhost/" + DATABASE + "?user=" + USER + "&password=" + PASSWORD; 
	            this.conn = DriverManager.getConnection(url);  //get a connection object
	            
	            String drop_userinfo ="DROP TABLE IF EXISTS USERINFO;";
	            String drop_relation ="DROP TABLE IF EXISTS RELATION;";
	            
	            Statement stmt = null;  
	    		stmt = conn.createStatement(); //get a statement object
	    		
	    		stmt.executeUpdate(drop_userinfo);
	    		stmt.executeUpdate(drop_relation);
	    		
	    		
	            String user_info = "CREATE TABLE USERINFO " +
	                         "(USERNAME    VARCHAR PRIMARY KEY     NOT NULL," +
	                         " PASSWORD    VARCHAR                 NOT NULL   , " +
	                         " AGE         INT   , " +
	                         " ID          INT);";
	            stmt.executeUpdate(user_info);  //execute statement on creating user info table
	            
	            
	            
	            String relation = "CREATE TABLE RELATION " +
	                         "(USERNAME VARCHAR  NOT NULL," +
	                         " FRIEND   VARCHAR   NOT NULL,"+
	                         " PRIMARY KEY(USERNAME, FRIEND)); ";
	            stmt.executeUpdate(relation);  // execute statement on creating relationship table

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
