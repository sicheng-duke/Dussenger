package com.server;
import com.auth.*;
import com.database.*;
import java.net.*;
import java.io.*;
import java.util.*;

import common.*;
public class Server {
	public static ArrayList<String> OnlineFriendList = new ArrayList<String> ();
	public ArrayList<String> getOnlineFriendList() {
		return OnlineFriendList;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//initialize();   //initialize database at first time 
		//UserAuth auth = new UserAuth();
		//auth.defaultInfo();
		
		
		try {
			String ss=InetAddress.getLocalHost().getHostAddress(); //get local machine's ip address
			System.out.println(ss);
			ServerSocket socket = new ServerSocket(6543);
			while(true)
			{
				Socket s = socket.accept();
				UserAuth auth = new UserAuth();
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				UserInfo u=(UserInfo)ois.readObject();
				if(u.getMsg_type() == 0)
				System.out.println(u.getUserId()+ " : " + u.getPasswd());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());				
				
				if(u.getMsg_type() == 0) //for login 
				{
					if(ManageThread.userOnline(u.getUserId()))
					{
						Message reply = new Message();
						reply.setMesType(MessageType.login_fail_usr_online);
						oos.writeObject(reply);
						s.close();
					}
					else if(u.getPasswd().equals(auth.checkPasswd(u.getUserId())))
					{
						OnlineFriendList.add(u.getUserId());
						Message reply = new Message();
						Relation rel = new Relation();
						reply.setGetter(u.getUserId());
						reply.setMesType(MessageType.login_success);
						reply.setFriendList((ArrayList)rel.getFriend(u.getUserId()));
						reply.setOnlineFriendList(OnlineFriendList);
						oos.writeObject(reply);
						ServerThread thread=new ServerThread(s);
						ManageThread.addThread(u.getUserId(), thread);
						thread.start();
						thread.NotifyOther_Login(u.getUserId());
						rel.closeConn();
					}
					else
					{
						Message reply = new Message();
						reply.setMesType(MessageType.login_fail);
						oos.writeObject(reply);
						s.close();
					}				
				}
				else if(u.getMsg_type() == 1)  //for register
				{
					
					Message reply = new Message();
					if(auth.checkName(u.getUserId()))
						{
							reply.setMesType(MessageType.register_sucess);
							auth.setUserInfo(u.getUserId()+"%:%"+u.getPasswd());
						}
						else
							reply.setMesType(MessageType.register_fail);
					
					oos.writeObject(reply);
					
					System.out.println(u.getUserId()+ " : " + u.getPasswd());
				}
				else if(u.getMsg_type() == 2)   //for ipconnect
				{
					Message reply = new Message();
					reply.setMesType(MessageType.connect_success);
					oos.writeObject(reply);
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
	
	
	
	
	public static void initialize()
	{
		init  initial = new init();
		initial.initDatabase();
		//UserAuth info = new UserAuth();
	}
	
}
