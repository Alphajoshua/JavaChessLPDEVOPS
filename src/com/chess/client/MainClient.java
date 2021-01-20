package com.chess.client;

import com.chess.client.account.AccountIHM;
import com.chess.common.Account;

public class MainClient {

	private static Client client;
	public static Client getClient() {
		return client;
	}
	
	private static Account account;
	public static Account getAccount() {
		return account;
	}
	public static void setAccount(Account account) {
		MainClient.account = account;
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			printUsage();
		} else {
			client = new Client(args[0], Integer.parseInt(args[1]));
			IHM.run(client, args);
			AccountIHM.run(args);
		}
	}

	private static void printUsage() {
		System.out.println("java client.Client <address> <port>");
		System.out.println("\t<address>: server's ip address");
		System.out.println("\t<port>: server's port");
	}
}
