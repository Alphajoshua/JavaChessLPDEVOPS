package com.chess.common.messages.login;

import com.chess.common.Account;
import com.chess.common.messages.ServerMessage;

public class LoginResult extends ServerMessage {

	private static final long serialVersionUID = 7845248587085921982L;
	private final Account loggedAccount;
	private final LoginResultType loginResult;
	
	public LoginResult(LoginResultType type, Account loggedAccount) {
		super(Account.SERVER_ACCOUNT);
		this.loginResult = type;
		this.loggedAccount = loggedAccount;
	}

	public Account getLoggedAccount() {
		return loggedAccount;
	}
	
	public LoginResultType getLoginResult() {
		return loginResult;
	}
	
	public static enum LoginResultType {
		LOGIN_SUCCESS(true, "Connection réussi !"),
		WRONG_PASSWORD(false, "Mauvaise mot de passe"),
		UNKNOW_ACCOUNT(false, "Compte inconnu"),
		REGISTER_SUCCESS(true, "Inscription effectué");
		
		private final boolean success;
		private final String message;
		
		private LoginResultType(boolean success, String message) {
			this.success = success;
			this.message = message;
		}
		
		public boolean isSuccess() {
			return success;
		}
		
		public String getMessage() {
			return message;
		}
	}
}
