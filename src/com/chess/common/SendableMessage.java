package com.chess.common;

import java.io.Serializable;

/**
 * Sendable message is the sent message between client and server.
 * 
 * @author Elikill58
 */
public abstract class SendableMessage implements Serializable {

	private static final long serialVersionUID = 656145611L;
	
	/**
	 * Edit the ID of the sender ID
	 * 
	 * @param id the ID of the sender
	 */
	public abstract void setId(int id);
	
	/**
	 * Get the sender ID
	 * 
	 * @return the sender ID
	 */
	public abstract int getId();
	
	/**
	 * Get the name of the sender
	 * 
	 * @return the sender name
	 */
	public abstract String getName();
	
	/**
	 * Compile message data to make it showable
	 * 
	 * @return a showable message
	 */
	public abstract String toShow();
}
