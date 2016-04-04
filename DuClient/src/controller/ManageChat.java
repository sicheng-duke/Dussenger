package controller;
import java.util.*;
import view.*;
public class ManageChat {
	//unread message
	private static HashMap hm=new HashMap<String, String>();
	//maininterface
	private static HashMap record = new HashMap<String, MainInterface>();
	
	private static HashMap gp_hm = new HashMap<String, String>();

	public static void addGPCon(String Getter,String content)
	{
		gp_hm.put(Getter, content);
	}
	
	public static String getGPCon(String Getter )
	{
		return (String) gp_hm.get(Getter);
	}

	public static void removeGPCon(String Getter)
	{
		gp_hm.remove(Getter);
	}
	
	public static boolean containGP(String Getter)
	{
		return gp_hm.containsKey(Getter);
	}
	
	public static boolean containFriend(String Getter)
	{
		return hm.containsKey(Getter);
	}


	public static void addView(String usr,MainInterface view)
	{
		record.put(usr, view);
	}
	
	public static MainInterface getView(String usr)
	{
		return (MainInterface) record.get(usr);
	}
	
 
	
	public static void addCon(String Getter,String content)
	{
		hm.put(Getter, content);
	}
	
	public static String getCon(String Getter )
	{
		return (String) hm.get(Getter);
	}

	public static void remove(String Getter)
	{
		hm.remove(Getter);
	}
}
