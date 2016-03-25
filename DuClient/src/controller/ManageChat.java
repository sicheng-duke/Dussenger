package controller;
import java.util.*;
import view.*;
public class ManageChat {
	//unread message
	private static HashMap hm=new HashMap<String, String>();
	//maininterface
	private static HashMap record = new HashMap<String, MainInterface>();
	
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
