package controller;

import java.util.ArrayList;
import java.util.HashMap;

import common.Message;

public class ManageRequest {
	private static ArrayList<Message> request_list = new ArrayList<Message>();
	
	public static ArrayList<Message> getRequest_list() {
		return request_list;
	}
	public static void addRequest(Message m)
	{
		request_list.add(m);
	}
	public static void clearRequest(Message m)
	{
		request_list.clear();
	}
	
	public static void removeRequest(int index)
	{
		request_list.remove(index);
	}
	
	
}
