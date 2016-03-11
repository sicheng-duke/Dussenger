package controller;

import java.util.*;

public class ManageThread {
	
	private static HashMap hm=new HashMap<String, ClientThread>();
	
	//addThread
	public static void addClientConServerThread(String qqId,ClientThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//getThread
	public static ClientThread getClientConServerThread(String qqId)
	{
		return (ClientThread)hm.get(qqId);
	}
	

}
