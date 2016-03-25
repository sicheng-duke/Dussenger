package com.server;
import common.*;
import java.io.*;
import java.net.*;

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

	public void run(){
		
		ObjectInputStream ois;
		try {
			while(true){
				ois = new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				if(m.getMesType().equals(MessageType.logout))
				{
					System.out.println("Use:"+m.getSender()+" logout");
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
	

	
	
