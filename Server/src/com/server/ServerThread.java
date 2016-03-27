package com.server;
import common.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;

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
	
	public void run(){
		
		ObjectInputStream ois;
		try {
			while(true){
				ois = new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				if(m.getMesType().equals(MessageType.logout))
				{
					new Server().OnlineFriendList.remove(m.getSender());
					System.out.println("User:"+m.getSender()+" logout");
					NotifyOther_Logoff(m.getSender());
					ManageThread.getThread(m.getSender()).interrupt();
					ManageThread.removeThread(m.getSender());
					return;
				}
				else
				{
					ServerThread thread = ManageThread.getThread(m.getGetter());
					ObjectOutputStream oos=new ObjectOutputStream(thread.s.getOutputStream());
					oos.writeObject(m);
				}
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
}
	

	
	
