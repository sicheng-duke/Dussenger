package com.auth;
import com.database.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*this part will add some test
 * user and relationship use for initial test
 */
public class InfoAdd {

	private Connection InfoConn;
	private Statement stmt;
	public InfoAdd()
	{
		connect infoAdd = new connect();
		infoAdd.connectDatabase();
		InfoConn = infoAdd.getConn();
		
	}
	public void defauleInfo()
	{
		String[] UserName = {"Abb","Bcc","Cdd","Dee","Eff","Fgg","Ghh","Hii"};
		String[] Password = {"123","234","345","456","567","678","789","890"};
		String[] Age = {"15","45","67","23","42","15","45","67"};
		try {
			for(int i = 0 ; i < 8 ; i++)
			{
				String s = UserName[i]+"%:%"+Password[i]+"%:%"+Age[i];
				this.setUserInfo(s);
			}
			for(int i = 0 ; i < 8; i++)
			{
				for(int j = 0 ; j < 8; j++)
				{
					if(i == j)
						continue;
					else
					{
						String s = UserName[i] + "%:%" + UserName[j];
						this.setRelation(s);
						
					}
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String authUsr(String ID) throws Throwable
	{
		stmt = InfoConn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM USERINFO WHERE USERNAME = '"+ ID + "';");
		if(rs.next())
			return rs.getString("PASSWORD");
		return "";
	}
	//insert user info into database
	public void setUserInfo(String Info) throws Throwable
	{
		String[] stringArray = Info.split("%:%");
		stmt = InfoConn.createStatement();
		String sql = "INSERT INTO USERINFO (USERNAME,PASSWORD,AGE,ID)"
					+"VALUES('" + stringArray[0]
					+"','"+stringArray[1]
					+"','"+Integer.parseInt(stringArray[2])
					+"',"+null
					+");";
		stmt.executeUpdate(sql);		
	}
	
	//insert relation into database
	public void setRelation(String Info) throws Throwable
	{
		String[] stringArray = Info.split("%:%");
		stmt = InfoConn.createStatement();
		String sql = "INSERT INTO RELATION (USERNAME,FRIEND)"
					+"VALUES('" + stringArray[0]
					+"','"+stringArray[1]
					+"');";
		stmt.executeUpdate(sql);
	}
	
	
	
}
