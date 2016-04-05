package com.server;
import common.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.auth.UserAuth;
import com.database.GroupChat;
import com.database.Relation;

public class ServerThread extends Thread {
	
	private Socket s;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ServerThread(Socket s){
		this.s = s;
	}
	
	public void NotifyOther_Login(String curr) {
		HashMap hm = ManageThread.hm;
		Iterator it = hm.keySet().iterator();
		Message m = new Message();
		m.setCon(curr);
		m.setMesType(MessageType.onLine_Friend);
		while (it.hasNext()) {
			//System.out.println(it.next().toString());
			String onLineUserID = it.next().toString();
			//System.out.println(onLineUserID);
			try{
				ObjectOutputStream oos = new ObjectOutputStream(ManageThread.getThread(onLineUserID).s.getOutputStream());
				m.setGetter(onLineUserID);
				oos.writeObject(m);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void NotifyOther_Logoff(String curr) {
		HashMap hm = ManageThread.hm;
		Iterator it = hm.keySet().iterator();
		Message m = new Message();
		m.setCon(curr);
		m.setMesType(MessageType.offLine_Friend);
		while (it.hasNext()) {
			//System.out.println(it.next().toString());
			String onLineUserID = it.next().toString();
			//System.out.println(onLineUserID);
			try{
				ObjectOutputStream oos = new ObjectOutputStream(ManageThread.getThread(onLineUserID).s.getOutputStream());
				m.setGetter(onLineUserID);
				oos.writeObject(m);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void returnGroup(Message m,String getter) throws Throwable
	{
		Message reply = new Message();
		GroupChat gp = new GroupChat();
		reply.setGetter(getter);
		reply.setMesType(MessageType.returnGroup);
		reply.setFriendList((ArrayList)gp.getGroup(m.getSender()));
		reply.setGroupMap(gp.getGPMap(reply.getFriendList()));
		ServerThread thread = ManageThread.getThread(getter);
		if(thread != null)
		{
			ObjectOutputStream oos = new ObjectOutputStream(thread.s.getOutputStream());
			oos.writeObject(reply);
		}
		gp.closeConn();
	}
	
	public void returnFriend(Message m) throws Throwable
	{
		Message reply = new Message();
		Relation rel = new Relation();
		reply.setGetter(m.getSender());
		reply.setMesType(MessageType.returnRelation);
		reply.setFriendList((ArrayList)rel.getFriend(m.getSender()));
		reply.setOnlineFriendList(Server.OnlineFriendList);
		ServerThread thread = ManageThread.getThread(m.getSender());
		ObjectOutputStream oos = new ObjectOutputStream(thread.s.getOutputStream());
		oos.writeObject(reply);
		rel.closeConn();
	}

	public boolean deleteGroup(String gpName,String usr) throws Throwable
	{
		GroupChat gp = new GroupChat();
		boolean result = gp.deleteGroup(gpName, usr);
		
		gp.closeConn();
		return result;
		//true delete owner's group->notify other
		//false delete member's group 
	}
	
	public void createGroup(Message m) throws Throwable
	{
		String name = m.getCon();
		GroupChat cpChat = new GroupChat();
		Message reply = new Message();
		reply.setGetter(m.getSender());
		if(cpChat.checkGroup(name))
		{
			reply.setMesType(MessageType.createSuccess);
			ArrayList<String> str = m.getFriendList();
			cpChat.setGroup(str, m.getCon());
		}
		else
		{
			reply.setMesType(MessageType.createFail);
		}
		ServerThread thread = ManageThread.getThread(m.getSender());
		ObjectOutputStream oos = new ObjectOutputStream(thread.s.getOutputStream());
		oos.writeObject(reply);
		cpChat.closeConn();	
	}
	
	public void run(){
		
		ObjectInputStream ois;
		try {
			while(true){
				ois = new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//logout information
				if(m.getMesType().equals(MessageType.logout))
				{
					new Server().OnlineFriendList.remove(m.getSender());
					System.out.println("User:"+m.getSender()+" logout");
					NotifyOther_Logoff(m.getSender());
					ManageThread.getThread(m.getSender()).interrupt();
					ManageThread.removeThread(m.getSender());
					return;
				}
				//get relation
				else if(m.getMesType().equals(MessageType.getRelation))
				{
					
					returnFriend(m);
				}
				else if(m.getMesType().equals(MessageType.deleteGroup))
				{
					GroupChat gp = new GroupChat();
					ArrayList<String> list = (ArrayList<String>) gp.getMember(m.getCon());
					gp.closeConn();
					if(deleteGroup(m.getCon(),m.getSender()))
					{
						for(String s:list)
						{
							returnGroup(m,s);
						}
					}
					else
					{
						returnGroup(m,m.getSender());
					}
					
				}
				//get group request
				else if(m.getMesType().equals(MessageType.getGroup))
				{
					returnGroup(m,m.getSender());
					
				}
				//create group
				else if(m.getMesType().equals(MessageType.createGroup))
				{
					createGroup(m);
					GroupChat gp = new GroupChat();
					ArrayList<String> list = (ArrayList<String>) gp.getMember(m.getCon());
					gp.closeConn();
					for(String s:list)
					{
						returnGroup(m,s);
					}
					
				}
				//change passwd
				else if(m.getMesType().equals(MessageType.change_passwd))
				{
					UserAuth usr = new UserAuth();
					usr.changePasswd(m.getSender(), m.getCon());
					usr.close();
				}
				// groupMessage Forwarding
				else if(m.getMesType().equals(MessageType.groupForward))
				{
					System.out.println("get message" + m.getCon());
					GroupChat gp = new GroupChat();				
					
					ArrayList<String> member = (ArrayList<String>) gp.getMember(m.getGetter());
					
					for(String name : member)
					{
						System.out.println("group mem "+name);
						Message reply = new Message();
						reply.setMesType(MessageType.groupMessage);
						reply.setCon(m.getCon());
						reply.setSender(m.getGetter());
						reply.setStarter(m.getSender());
						ServerThread thread = ManageThread.getThread(name);
						if(thread != null)
						{
							ObjectOutputStream oos=new ObjectOutputStream(thread.s.getOutputStream());
							oos.writeObject(reply);
						}
						
					}
	
				}
				//message forwarding
				else
				{
					//System.out.println("from "+m.getSender()+" to " + m.getSender() + ": "+ m.getCon());
					
					ServerThread thread = ManageThread.getThread(m.getGetter());
					if(thread != null)
					{
						ObjectOutputStream oos=new ObjectOutputStream(thread.s.getOutputStream());
						oos.writeObject(m);
					}
				}
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	
}
	

	
	
