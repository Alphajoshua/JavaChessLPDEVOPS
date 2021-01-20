package com.chess.client;

import com.chess.client.chat.ChatIHM;

public class MainClient {

	//STATIC CLIENT
	public static Client client;
	
	public static void main(String[] args) {
		if (args.length != 2) 
		{
			printUsage();
		} 
		else 
		{
			client = new Client(args[0],Integer.parseInt(args[1]));
			ChatIHM.main(args);
		}
	}

	private static void printUsage() {
		System.out.println("java client.Client <address> <port>");
		System.out.println("\t<address>: server's ip address");
		System.out.println("\t<port>: server's port");
	}
}
