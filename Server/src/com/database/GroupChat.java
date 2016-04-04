package com.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import com.auth.*;

import java.util.*;
public class GroupChat {

	private   Connection conn;

	private Statement stmt;
	public GroupChat()
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
	
	public void setGroup(ArrayList<String> list,  String name) throws Throwable
	{
		String member = "";
		for(int i = 0; i < list.size()-1 ;i++)
		{
			stmt = conn.createStatement();
			String gpRec = "INSERT INTO GPRECORD (USRNAME,GPNAME)"
					+"VALUES('" + list.get(i)
					+"','"+name
					+"');";
			stmt.executeUpdate(gpRec);
			member = member+list.get(i)+"%:%";
			
		}
		
		String Owner = list.get(list.size()-1);
		member = member + Owner;
		//add owner
		stmt = conn.createStatement();
		String gpRec = "INSERT INTO GPRECORD (USRNAME,GPNAME)"
				+"VALUES('" + Owner
				+"','"+name
				+"');";
		stmt.executeUpdate(gpRec);
		
		//add whole relation
		String GroupName = name;
		stmt = conn.createStatement();
		String sql = "INSERT INTO GPCHAT (NAME,OWNER,MEMBER)"
					+"VALUES('" + GroupName
					+"','"+Owner
					+"','"+member
					+"');";
		stmt.executeUpdate(sql);
	}
	
	public boolean checkGroup(String name) throws Throwable
	{
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT NAME FROM GPCHAT WHERE NAME = '"+ name + "';");
		if(rs.next())
		{
			return false;
		}
		return true;
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
