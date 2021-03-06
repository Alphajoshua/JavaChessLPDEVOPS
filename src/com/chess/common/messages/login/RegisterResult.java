package com.chess.common.messages.login;

import com.chess.common.Account;
import com.chess.common.messages.ServerMessage;

@SuppressWarnings("serial")
public class RegisterResult extends ServerMessage {

	private final Account registeredAccount;
	private final RegisterResultType registerResult;
	
	public RegisterResult(RegisterResultType type, Account registeredAccount) {
		super(Account.SERVER_ACCOUNT);
		this.registerResult = type;
		this.registeredAccount = registeredAccount;
	}

	public Account getRegisteredAccount() {
		return registeredAccount;
	}
	
	public RegisterResultType getRegisterResult() {
		return registerResult;
	}
	
	public static enum RegisterResultType {
		REGISTER_SUCCESS(true, "Connection réussi !"),
		INTERNAL_ERROR(false, "Une erreur interne est survenu.");
		
		private final boolean success;
		private final String message;
		
		private RegisterResultType(boolean success, String message) {
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
