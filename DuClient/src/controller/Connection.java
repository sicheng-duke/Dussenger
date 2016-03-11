package controller;
import java.util.*;
import java.net.*;
import java.io.*;
import controller.*;
import common.*;
public class Connection {
	
	public Socket s;
	public boolean loginInfo(Object login)
	{
		try {
			s = new Socket("10.190.49.226",6541);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(login);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message m = (Message)ois.readObject();
			if(m.getMesType().equals(MessageType.login_success))
			{
				
				ClientThread thread=new ClientThread(s);
				thread.start();
				ManageThread.addClientConServerThread(((UserInfo)login).getUserId(), thread);
				return true;
			}
			//return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
		
	}

}
