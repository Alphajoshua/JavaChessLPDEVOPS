package com.chess.common.messages;

import com.chess.common.Account;

/**
 * Server message are sent to communicate with server only
 * 
 * @author Elikill58
 */
@SuppressWarnings("serial")
public abstract class ServerMessage extends SendableMessage {

	public ServerMessage(Account acc) {
		super(acc);
	}
	
	@Override
	public String toShow() {
		return null;
	}
}
