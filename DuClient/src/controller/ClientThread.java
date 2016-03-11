package controller;

import java.io.*;
import java.net.*;

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
			
		}
	}
	

	
	

}
