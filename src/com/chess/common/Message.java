package com.chess.common;

public class Message extends SendableMessage {

	private static final long serialVersionUID = 65156145614561L;

	private int id;
	private String name;
	private String message;
	
	/**
	 * Create a new message without give sender
	 * 
	 * @param id the sender ID
	 * @param message the message
	 */
	public Message(int id, String message) {
		this.id = id;
		this.message = message;
	}
	
	/**
	 * Create a new message
	 * 
	 * @param id the sender ID
	 * @param message the message
	 * @param sender the name of sender
	 */
	public Message(int id, String message, String sender) {
		this.id = id;
		this.message = message;
		this.name = sender;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
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
	 * Set a new sender name
	 * 
	 * @param sender the new name of sender
	 */
	public void setName(String sender) {
		this.name = sender;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toShow() {
		return name + " > " + message;
	}
}
