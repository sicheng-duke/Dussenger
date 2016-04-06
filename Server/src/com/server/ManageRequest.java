package com.server;
import java.util.*;
import common.*;
public class ManageRequest {
	
	public static HashMap hm=new HashMap<String, ArrayList<Message>>();
	
	public static void storeReq(String s,Message m)
	{
		if(hm.containsKey(s))
		{
			ArrayList<Message> tmp = (ArrayList<Message>) hm.get(s);
			tmp.add(m);
			hm.put(s, tmp);
		}
		else
		{
			ArrayList<Message> tmp = new ArrayList<Message>();
			tmp.add(m);
			hm.put(s, tmp);
		}
		
	}
	
	public static ArrayList<Message> getReq(String s)	
	{
		return (ArrayList<Message>) hm.get(s);
	}
	
	public static void removeReq(String s)
	{
		hm.remove(s);
	}

}
