package com.chess.common.messages;

import com.chess.common.Account;

/**
 * Server message are sent to communicate with server only
 * 
 * @author Elikill58
 */
public abstract class ServerMessage extends SendableMessage {

	private static final long serialVersionUID = 7852174356887803576L;

	public ServerMessage(Account acc) {
		super(acc);
	}
	
	@Override
	public String toShow() {
		return null;
	}
}
