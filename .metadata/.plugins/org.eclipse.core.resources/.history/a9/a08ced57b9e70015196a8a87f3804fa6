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
		while(true)
		{
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				ServerThread thread = ManageThread.getThread(m.getGetter());
				ObjectOutputStream oos=new ObjectOutputStream(thread.s.getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
	

	
	
