package com.chess.common.messages.login;

import com.chess.common.Account;
import com.chess.common.messages.ServerMessage;

@SuppressWarnings("serial")
public class LoginResult extends ServerMessage {

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
		LOGIN_SUCCESS(true, "Connection r�ussi !"),
		WRONG_PASSWORD(false, "Mauvaise mot de passe"),
		UNKNOW_REQUEST(false, "Requ�te inconnu"),
		UNKNOW_ACCOUNT(false, "Compte inconnu");
		
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
