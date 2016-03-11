package controller;
import java.util.*;
import java.net.*;
import java.io.*;
import controller.*;
import common.*;
public class Connection {
	
	public Socket s;
	public void loginInfo(Object login)
	{
		try {
			s = new Socket("10.190.53.53",6666);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(login);
			ClientThread thread=new ClientThread(s);
			thread.start();
			ManageThread.addClientConServerThread(((UserInfo)login).getUserId(), thread);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
