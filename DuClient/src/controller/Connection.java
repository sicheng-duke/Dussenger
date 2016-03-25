package controller;
import java.util.*;

import javax.swing.JOptionPane;

import java.net.*;
import java.io.*;
import controller.*;
import common.*;
public class Connection {
	
	public Socket s;
	private String server_ip = IPManage.getIP();
	
	public String getServer_ip() {
		return server_ip;
	}

	public boolean register(Object usr_info)
	{
		try {
			s = new Socket();
			s.connect(new InetSocketAddress(getServer_ip(),6543), 1000);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(usr_info);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message m = (Message)ois.readObject();
			if(m.getMesType().equals(MessageType.register_sucess))
			{
				System.out.println("register success");
				return true;
			}
			s.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}
	
	public boolean loginInfo(Object login)
	{
		try {
			s = new Socket();
			s.connect(new InetSocketAddress(getServer_ip(),6543), 1000);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(login);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message m = (Message)ois.readObject();
			if(m.getMesType().equals(MessageType.login_success))
			{
				
				ClientThread thread=new ClientThread(s);
				RelationManage.setRelation(m.getFriendList());
				thread.start();
				ManageThread.addClientConServerThread(((UserInfo)login).getUserId(), thread);
				return true;
			}
			else if(m.getMesType().equals(MessageType.login_fail_usr_online))
			{
				return false;
			}
			//return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
		
	}
	
	public boolean first_connect(Object conn)
	{
		
		try {
			s = new Socket();
			s.connect(new InetSocketAddress(getServer_ip(),6543), 1000);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(conn);

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message m = (Message)ois.readObject();
			if(m.getMesType().equals(MessageType.connect_success))
			{
				
				System.out.println("connect success");
				return true;
			}
			s.close();
			//return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}

}
