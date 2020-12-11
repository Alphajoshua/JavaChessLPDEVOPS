package com.chess.common;

public class StatusUpdate extends SendableMessage {

	private static final long serialVersionUID = 656145611L;
	
	private final StatusType type;
	private final String name;
	private int id;
	
	/**
	 * Create a new status update 
	 * 
	 * @param type the status type
	 * @param id the sender ID
	 * @param userName the sender name
	 */
	public StatusUpdate(StatusType type, int id, String userName) {
		this.type = type;
		this.id = id;
		this.name = userName;
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
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toShow() {
		return "[Status] " + name + " s'est " + type.getName() + " !";
	}
	
	public static enum StatusType {
		LOGIN("connecté"),
		LOGOUT("déconnecté");
		
		private final String name;
		
		private StatusType(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
}
