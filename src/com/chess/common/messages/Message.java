package com.chess.common.messages;

import com.chess.common.Account;

public class Message extends SendableMessage {
	
	private static final long serialVersionUID = 3204261393535216623L;
	
	private String message;
	
	/**
	 * Create a new empty message
	 * 
	 */
	public Message()
	{
		message = "";
	}
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
	 * @param sender the name of sender
	 */
	public Message(Account sender, String message) {
		super(sender);
		this.message = message;
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
	
	@Override
	public String toShow() {
		String result = "";
		/*if(TODO.getLastSender() == name)
		{
			for(int i =0; i< name.length()+2;++i)
			{
				result +=" ";
			}
			result+= message;
		}
		else*/
			result = getSender().getName() + ": " + message;
		return result;
	}
}
