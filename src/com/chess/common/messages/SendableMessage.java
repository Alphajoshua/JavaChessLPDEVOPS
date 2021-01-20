package com.chess.common.messages;

import java.io.Serializable;

import com.chess.common.Account;

/**
 * Sendable message are the sent message between client to client thanks to server
 * 
 * @author Elikill58
 */
public abstract class SendableMessage implements Serializable {

	private static final long serialVersionUID = 656145611L;
	
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
	 * Edit the ID of the sender ID
	 * 
	 * @param id the ID of the sender
	 * @deprecated use {@link #getSender()} instead
	 */
	@Deprecated
	public abstract void setId(int id);
	
	/**
	 * Get the sender ID
	 * 
	 * @return the sender ID
	 * @deprecated use {@link #getSender()} instead
	 */
	@Deprecated
	public abstract int getId();
	
	/**
	 * Get the name of the sender
	 * 
	 * @return the sender name
	 * @deprecated use {@link #getSender()} instead
	 */
	@Deprecated
	public abstract String getName();
	
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
