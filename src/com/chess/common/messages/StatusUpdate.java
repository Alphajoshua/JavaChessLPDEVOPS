package com.chess.common.messages;

import com.chess.common.Account;

@SuppressWarnings("serial")
public class StatusUpdate extends SendableMessage {
	
	private final StatusType type;
	
	/**
	 * Create a new status update
	 * Used for stream
	 */
	public StatusUpdate() {
		this.type = StatusType.LOGIN;
	}
	
	/**
	 * Create a new status update 
	 * 
	 * @param type the status type
	 * @param id the sender ID
	 * @param userName the sender name
	 */
	public StatusUpdate(StatusType type, Account account) {
		super(account);
		this.type = type;
	}
	
	/**
	 * Get the status of the update
	 * 
	 * @return the state type
	 */
	public StatusType getType() {
		return type;
	}
	
	@Override
	public String toShow() {
		return "[Status] " + getSender().getName() + " s'est " + type.getName() + " !";
	}
	
	public static enum StatusType {
		LOGIN("connecté"),
		LOGOUT("déconnecté");
		
		private static final long serialVersionUID = 0;
		private final String name;
		
		private StatusType(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
}
