package controller;

import java.io.*;
import java.net.*;
import common.*;
import view.*;
public class ClientThread extends Thread {
	
	private Socket s;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientThread(Socket s){
		this.s = s;
	}
	
	public void run(){
		while(true)
		{
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				System.out.println("Sender: " + m.getSender()+"to");
				System.out.println("Getter: " + m.getGetter());
				System.out.println("Message:"+m.getCon()+"\n");
				
				MainInterface main= ManageChat.getView("1");
				
				System.out.println(main.getTarget());
				if(main.getTarget().equals(m.getSender()))
					main.showMessage(m);
				else{
					String s=ManageChat.getCon(m.getSender());
					s = s + m.getCon();
					ManageChat.addCon(m.getSender(), s);
				}

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

	
	

}
