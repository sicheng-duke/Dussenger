package com.test;
import com.database.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class InfoAdd {

	private Connection InfoConn;
	private Statement stmt;
	public InfoAdd()
	{
		connect infoAdd = new connect();
		infoAdd.connectDatabase();
		InfoConn = infoAdd.getConn();
		String[] UserName = {"Abb","Bcc","Cdd","Dee","Eff","Fgg","Ghh","Hii"};
		String[] Password = {"123","234","345","456","567","678","789","890"};
		String[] Age = {"15","45","67","23","42","15","45","67"};
		
		
		
		try {
			for(int i = 0 ; i < 8 ; i++)
			{
				String s = UserName[i]+"%:%"+Password[i]+"%:%"+Age[i];
				System.out.println(s);
				this.setUserInfo(s);
			}			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infoAdd.close();
		
	}
	
	
	public void setUserInfo(String Info) throws Throwable
	{
		String[] stringArray = Info.split("%:%");
		stmt = InfoConn.createStatement();
		
		System.out.println("1e1je2le1jel2je");
		String sql = "INSERT INTO USERINFO (USERNAME,PASSWORD,AGE,ID)"
					+"VALUES('" + stringArray[0]
					+"','"+stringArray[1]
					+"','"+Integer.parseInt(stringArray[2])
					+"',"+null
					+");";
		stmt.executeUpdate(sql);		
	}
}
