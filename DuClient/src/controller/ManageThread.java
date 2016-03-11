package controller;

import java.util.*;

public class ManageThread {
	
	private static HashMap hm=new HashMap<String, ClientThread>();
	
	//addThread
	public static void addClientConServerThread(String id,ClientThread ccst)
	{
		hm.put(id, ccst);
	}
	
	//getThread
	public static ClientThread getClientConServerThread(String id)
	{
		return (ClientThread)hm.get(id);
	}
	

}
