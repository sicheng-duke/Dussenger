package com.server;
import com.database.*;
import com.test.*;
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("server");
		//initialize();
		InfoAdd info = new InfoAdd();
		
		}
	
	
	public static void initialize()
	{
		init  initial = new init();
		initial.initDatabase();
	}
}
