package com.server;
import com.database.*;
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("server");
		initialize();

		
		}
	
	
	public static void initialize()
	{
		init  initial = new init();
		initial.initDatabase();
	}
}
