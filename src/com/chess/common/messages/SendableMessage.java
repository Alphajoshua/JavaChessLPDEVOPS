package com.chess.common.messages;

import java.io.Serializable;

import com.chess.common.Account;

/**
 * Sendable message are the sent message between client to client thanks to server
 * 
 * @author Elikill58
 */
@SuppressWarnings("serial")
public abstract class SendableMessage implements Serializable {
	
	private Account account;
	
	/**
	 * Create a new sendable message
	 * 
	 * Use {@link #SendableMessage(Account)} instead
	 */
	public SendableMessage() {

	}
	
	/**
	 * Create a new sendable message
	 * 
	 * @param acc the sender account
	 */
	public SendableMessage(Account acc) {
		this.account = acc;
	}
	
	/**
	 * Get account of the sender
	 * 
	 * @return the user account
	 */
	public Account getSender() {
		return account;
	}
	
	/**
	 * Set the user account of the sender
	 * 
	 * @param the user account
	 */
	public void setSender(Account acc) {
		this.account = acc;
	}
	
	/**
	 * Compile message data to make it showable
	 * 
	 * @return a showable message
	 */
	public abstract String toShow();
}
