package com.chess.common.messages;

import com.chess.common.Account;

@SuppressWarnings("serial")
public class Message extends SendableMessage {
		
	private String message;
	private Account with;
	
	/**
	 * Create a new message by copy
	 * 
	 */
	public Message(Message raw)
	{
		super(raw.getSender());
		message = raw.getMessage();
	}
	
	/**
	 * Create a new message
	 * 
	 * @param sender the sender account
	 * @param message the message
	 * @param with the player concerned by the message
	 */
	public Message(Account sender, String message, Account with) {
		super(sender);
		this.message = message;
		this.with = with;
	}
	
	/**
	 * Get the message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set a new text in message
	 * 
	 * @param message the new text of message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Get the account with the sender interact with
	 * It can be null if the message is a general one
	 * 
	 * @return the with account
	 */
	public Account getWith() {
		return with;
	}
		
	@Override
	public String toShow() {
		return getSender().getName() + ": " + message;
	}
	
	@Override
	public String toString() {
		return "[sender=" + getSender() + ",message=" + message + ",with=" + with + "]";
	}
}
