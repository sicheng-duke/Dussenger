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
			rs = stmt.executeQuery("SELECT GPNAME FROM GPRECORD WHERE GPNAME = '"+ name + "';");
			if(rs.next())
				return false;
		}
		return true;
	}
	
	 public boolean checkMember(String name, String gpName) throws Throwable
	 {
		 stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery("SELECT * FROM GPRECORD WHERE USRNAME = '"+ name + "' AND GPNAME = '"+gpName+"';");
		 if(rs.next())
		 {
			 return false;
		 }
		 return true;
	 }

	public List<String> getMember(String s) throws Throwable
	{	
		List<String> result = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT MEMBER FROM GPCHAT WHERE NAME = '"+ s + "';");
		while(rs.next())
		{
			String[] stringArray = rs.getString("MEMBER").split("%:%");
			for(String name:stringArray)
			{
				result.add(name);
			}
			
		}
		return result;
	}
	public HashMap<String,String> getGPMap(ArrayList<String> list) throws Throwable
	{
		HashMap<String,String> result =new HashMap<String,String>();
		for(String s:list)
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MEMBER FROM GPCHAT WHERE NAME = '"+ s + "';");
			while(rs.next())
			{
				result.put(s, rs.getString("MEMBER"));
			}
		}
		return result;
	}
	
	
	public boolean deleteGroup(String gpName, String owner) throws Throwable
	{
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT MEMBER FROM GPCHAT WHERE NAME = '"+ gpName + "' AND OWNER = '"+owner+"';");
		
		if(rs.next())
		{
			 stmt.execute("DELETE FROM GPCHAT WHERE NAME = '"+ gpName + "';");
			 stmt.execute("DELETE FROM GPRECORD WHERE GPNAME = '"+ gpName + "';");
			 return true;
			 //owner
		}
		else
		{
			ResultSet rs1 = stmt.executeQuery("SELECT MEMBER FROM GPCHAT WHERE NAME = '"+ gpName + "';");
			
			if(rs1.next())
			{
				String s = rs1.getString("MEMBER").replace(owner+"%:%", "");
				stmt.execute("UPDATE GPCHAT SET MEMBER = '"+s+"' WHERE NAME = '"+ gpName + "';");				
				stmt.execute("DELETE FROM GPRECORD WHERE GPNAME = '"+ gpName + "' AND USRNAME = '"+owner+"';");
			}
		}
		return false;
	}
	
	public void updateGroup(String usr, String gpName) throws Throwable
	{
		stmt = conn.createStatement();
		ResultSet rs1 = stmt.executeQuery("SELECT MEMBER FROM GPCHAT WHERE NAME = '"+ gpName + "';");
		if(rs1.next())
		{
			String s = usr + "%:%" + rs1.getString("MEMBER") ;
			stmt.execute("UPDATE GPCHAT SET MEMBER = '"+s+"' WHERE NAME = '"+ gpName + "';");
			stmt.execute("INSERT INTO GPRECORD (USRNAME,GPNAME)"
					+"VALUES('" + usr
					+"','"+gpName
					+"');");
			
		}
	}
	
	public List<String>  getGroup(String s) throws Throwable
	{
		List<String> result = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT GPNAME FROM GPRECORD WHERE USRNAME = '"+ s + "';");
		while(rs.next())
		{
			result.add(rs.getString("GPNAME"));
		}
		return result;
	}
	
	
}
