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
		reply.setFriendList((ArrayList)gp.getGroup(getter));
		reply.setGroupMap(gp.getGPMap(reply.getFriendList()));
		ServerThread thread = ManageThread.getThread(getter);
		if(thread != null)
		{
			ObjectOutputStream oos = new ObjectOutputStream(thread.s.getOutputStream());
			oos.writeObject(reply);
		}
		gp.closeConn();
	}
	
	public void deleteFriend(Message m) throws Throwable
	{
		Relation rl = new Relation();
		rl.deleteRelation(m.getGetter(), m.getSender());
		Message m1 = new Message();
		m1.setMesType(MessageType.delete_friend);
		m1.setGetter(m.getSender());
		m1.setSender(m.getGetter());
		returnFriend(m);
		returnFriend(m1);
		rl.closeConn();
		
		
	}
	
	public void noSuchUsr(Message m) throws Throwable
	{
		Message reply = new Message();
		reply.setGetter(m.getSender());
		reply.setMesType(MessageType.add_friend_not_exist);
		ServerThread thread = ManageThread.getThread(m.getSender());
		ObjectOutputStream oos = new ObjectOutputStream(thread.s.getOutputStream());
		oos.writeObject(reply);
	}
	
	public void forwardRequest(Message m,String type,String filePath) throws Throwable
	{
		//System.out.println("send file from" + m.getSender() + " to " + m.getSender());
		Message reply = new Message();
		reply.setSender(m.getSender());
		String s = null;
		if(m.getMesType().equals(MessageType.add_friend))
		{
			reply.setGetter(m.getCon());
			s = "Friend " + m.getSender() + " wants to add you";
		}
		else
		{
			reply.setGetter(m.getGetter());
			s = "Friend " + m.getSender() + " wants to send file to you";
		}
		reply.setPath(filePath);
		reply.setMesType(type);
		
		reply.setCon(s);
		ServerThread thread = ManageThread.getThread(reply.getGetter());
		if(thread != null)
		{
			ObjectOutputStream oos = new ObjectOutputStream(thread.s.getOutputStream());
			oos.writeObject(reply);
		}
		else
		{
			ManageRequest.storeReq(reply.getGetter(), reply);
		}
		
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
		if(thread != null)
		{
			ObjectOutputStream oos = new ObjectOutputStream(thread.s.getOutputStream());
			oos.writeObject(reply);
			NotifyOther_Login(reply.getGetter());
		}
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
			for(String s :str)
				System.out.println("frinend" + s);
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
	
	public void updateGroup(Message m, String gpName) throws Throwable
	{
		GroupChat gpChat = new GroupChat();
		if(gpChat.checkMember(m.getCon(), gpName))
			gpChat.updateGroup(m.getCon(), gpName);
		gpChat.closeConn();
				
	}
	
	public void fileSend(Message m) throws Throwable
	{
		ServerSocket s = new ServerSocket(4567);
		Socket socket = null;
		socket = s.accept();
		File file = new File(m.getPath());	
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(m.getPath())));
		int buffferSize = 10240;
        byte[]bufArray=new byte[buffferSize];  
        dos.writeUTF(file.getName());   
        dos.flush();   
        dos.writeLong((long) file.length());   
        dos.flush();   
        while (true) {   
            int read = 0;   
            if (dis!= null) {   
              read = dis.read(bufArray);   
            }   

            if (read == -1) {   
              break;   
            }   
            dos.write(bufArray, 0, read);   
          }   
          dos.flush(); 
          if(dos != null) 
				dos.close();
          if(dis != null)
        	  dis.close();
          if(socket != null)
        	  socket.close();
          s.close();
		
	}
	
	public String  fileReceive(Message m) throws Throwable
	{
		System.out.println("file receive from" + m.getSender() + " to " + m.getGetter());
		ServerSocket s = new ServerSocket(3456);
		Socket socket = null;

		socket = s.accept();
		DataInputStream dis = null;
		dis = new DataInputStream(new BufferedInputStream(socket   
                .getInputStream()));  
        int bufferSize = 10240;    
        byte[] buf = new byte[bufferSize];   
        int passedlen = 0;   
        long len = 0;  
        int  i = 0;
        String savePath = "/tmp/"+m.getGetter()+"-";
        savePath += dis.readUTF();   
        DataOutputStream fileOut = new DataOutputStream(   
            new BufferedOutputStream(new BufferedOutputStream(   
                new FileOutputStream(savePath))));   
        len = dis.readLong();  
        while (true) 
        {   
            int read = 0;   
            if (dis!= null) 
            {   
              read = dis.read(buf);   
            }   
            passedlen += read;   
            if (read == -1) 
            {   
              break;   
            }   
            System.out.println("File Receive from" + m.getSender() + (passedlen * 100 / len) + "%");   
            fileOut.write(buf, 0, read);   
            i++;
          } 
        fileOut.close(); 
        
        s.close();
        socket.close();
        return savePath;
		
	}
	
	public void replyRequest(Message m, String type) throws Throwable
	{
		UserAuth auth = new UserAuth();
		if(type.equals(MessageType.accept_add_request))
		{
			auth.setRelation(m.getGetter()+"%:%"+m.getSender());
			auth.setRelation(m.getSender()+"%:%"+m.getGetter());
		}
		Message reply_to_sender = new Message();
		Message reply_to_getter = new Message();
		reply_to_sender.setMesType(type);
		reply_to_sender.setGetter(m.getSender());
		reply_to_sender.setSender(m.getGetter());
		
		reply_to_getter.setMesType(type);
		reply_to_getter.setGetter(m.getGetter());
		reply_to_getter.setSender(m.getSender());
		
		
		
		ServerThread thread_sender = ManageThread.getThread(m.getSender());
		if(thread_sender != null )
		{
			ObjectOutputStream oos = new ObjectOutputStream(thread_sender.s.getOutputStream());
			oos.writeObject(reply_to_sender);
		}

		
		ServerThread thread_getter = ManageThread.getThread(m.getGetter());
		if(thread_getter != null)
		{
			ObjectOutputStream oos = new ObjectOutputStream(thread_getter.s.getOutputStream());
			oos.writeObject(reply_to_getter);
			
		}
		auth.close();
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
				// 
				else if(m.getMesType().equals(MessageType.accept_add_request))
				{
					
					//check whether they are friend
					Relation rl = new Relation();
					if(rl.checkRelation(m.getSender(), m.getSender()))
					{
						replyRequest(m,MessageType.accept_add_request);
					}
					rl.closeConn();
					
					System.out.println("receive accept");
					
				}
				else if(m.getMesType().equals(MessageType.receive_file))
				{
					fileSend(m);
				}
				else if(m.getMesType().equals(MessageType.delete_friend))
				{					
					deleteFriend(m);
				}
				//send file req
				else if(m.getMesType().equals(MessageType.send_file_req))
				{
					String path = fileReceive(m);
					forwardRequest(m,MessageType.send_file_req,path);
					
				}
				else if(m.getMesType().equals(MessageType.denny_add_request))
				{
					Relation rl = new Relation();
					if(rl.checkRelation(m.getSender(), m.getSender()))
					{
						replyRequest(m,MessageType.denny_add_request);
					}
					rl.closeConn();
					System.out.println("receive deny");
				}
				else if(m.getMesType().equals(MessageType.update_group))
				{
					
					ArrayList<String> list = m.getFriendList();
					
					for(String s : list)
					{
						updateGroup(m,s);
					}
					returnGroup(m,m.getCon());
				}
				//get relation
				else if(m.getMesType().equals(MessageType.getRelation))
				{					
					returnFriend(m);
				}
				
				//delete Group
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
					
					for(String s:list)
					{
						returnGroup(m,s);
					}
					gp.closeConn();
				}
				//
				else if(m.getMesType().equals(MessageType.add_friend))
				{

					UserAuth usr = new UserAuth();
					if(usr.checkName(m.getCon()))
					{

						noSuchUsr(m);
					}
					else
					{

						forwardRequest(m,MessageType.add_request,null);
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
				else if(m.getMesType().equals(MessageType.default_type))
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
	

	
	
