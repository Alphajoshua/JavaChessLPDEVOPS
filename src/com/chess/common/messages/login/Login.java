package com.chess.common.messages.login;

import com.chess.common.messages.ServerMessage;

@SuppressWarnings("serial")
public class Login extends ServerMessage {
	
	private final String name, passwd;
	
	public Login(String name, String hashedPasswd) {
		super(null);
		this.name = name;
		this.passwd = hashedPasswd;
	}
	
	public String getLogin() {
		return name;
	}
	
	public String getPassword() {
		return passwd;
	}
}
