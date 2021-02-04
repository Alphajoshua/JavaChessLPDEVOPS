package com.chess.common.messages.login;

import com.chess.common.messages.ServerMessage;

public class Login extends ServerMessage {
	
	private static final long serialVersionUID = -6302469239250041764L;
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
