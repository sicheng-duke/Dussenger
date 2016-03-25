package controller;
import java.util.*;
public class IPManage {

	private static HashMap IPManage = new HashMap<String,String>();
	
	public static void setIP(String IP)
	{
		IPManage.put("server",IP);
	}
	public static String getIP()
	{
		return (String) IPManage.get("server");
	}
	public static void removeIP()
	{
		IPManage.remove("server");
	}
	
}
