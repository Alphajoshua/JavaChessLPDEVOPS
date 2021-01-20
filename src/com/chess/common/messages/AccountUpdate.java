package com.chess.common.messages;

import com.chess.common.Account;

@SuppressWarnings("serial")
public class AccountUpdate extends ServerMessage {
	
	private final AccountAction action;
	
	public AccountUpdate(Account acc, AccountAction action) {
		super(acc);
		this.action = action;
	}
	
	public AccountAction getAction() {
		return action;
	}
	
	public static enum AccountAction {
		CREATE,
		SAVE,
		REMOVE,
		ACTION_ACCEPTED,
		ACTION_REMOVED;
	}
}
