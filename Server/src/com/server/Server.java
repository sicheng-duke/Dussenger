package com.server;
import com.auth.*;
import com.database.*;
import java.net.*;
import java.io.*;
import java.util.*;

import common.*;
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("server");
		//initialize();
		//relation rl = new relation();
		
		try {
			String ss=InetAddress.getLocalHost().getHostAddress();
			System.out.println(ss);
			ServerSocket socket = new ServerSocket(6541);
			while(true)
			{
				Socket s = socket.accept();
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				UserInfo u=(UserInfo)ois.readObject();
				System.out.println(u.getUserId()+ " : " + u.getPasswd());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123"))
				{
					Message reply = new Message();
					reply.setMesType(MessageType.login_success);
					oos.writeObject(reply);
					ServerThread thread=new ServerThread(s);
					ManageThread.addThread(u.getUserId(), thread);
					thread.start();
				}
				else
				{
					Message reply = new Message();
					reply.setMesType(MessageType.login_fail);
					oos.writeObject(reply);
					s.close();
				}
					
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}
	
	
	
	
	public static void initialize()
	{
		init  initial = new init();
		initial.initDatabase();
		InfoAdd info = new InfoAdd();
	}
}
