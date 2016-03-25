package com.server;

import java.util.*;

public class ManageThread {

	private static HashMap hm=new HashMap<String, ServerThread>();
	
	//addThread
	public static void addThread(String id,ServerThread thread)
	{
		hm.put(id, thread);
	}
	
	//getThread
	public static ServerThread getThread(String id)
	{
		return (ServerThread)hm.get(id);
	}
	
	public static void removeThread(String id)
	{
		hm.remove(id);
		
	}
	
	public static boolean userOnline(String id)
	{
		return hm.containsKey(id);
		
	}

}
