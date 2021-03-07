package com.chess.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.chess.server.manager.OldGameManager;

@SuppressWarnings("serial")
public class Account implements Serializable {
	
	public static final Account SERVER_ACCOUNT = new Account(-1, "Server", null);
	
	private final long id;
	private String name;
	private Timestamp createdAt;
	private final List<OldGame> oldGames = new ArrayList<>();
	private boolean isTemp = false;

	/**
	 * New account just created from database
	 * 
	 * @param id the account id
	 * @param name the account name
	 */
	public Account(long id, String name, Timestamp createdAt) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		load();
	}
	
	/**
	 * New temporary account
	 * 
	 * @param id the account id
	 * @param name the account name
	 * @param temp if the account is temp
	 */
	public Account() {
		this.id = -1;
		this.name = "Not logged";
		this.isTemp = true;
		this.createdAt = null;
	}
	
	private void load() {
		new Thread(() -> oldGames.addAll(OldGameManager.loadAllGamesOf(getId())));
	}
	
	/**
	 * Get the account ID
	 * 
	 * @return the account id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Get the user name
	 * 
	 * @return the name of user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set a new name of the user
	 * 
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Check if it's temporary account
	 * 
	 * @return true if it's a temp account
	 */
	public boolean isTemp() {
		return isTemp;
	}
	
	/**
	 * Know when the account got created.
	 * 
	 * @return the created timestamp
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Account))
			return false;
		Account o = (Account) obj;
		return super.equals(obj) || (id == o.id && name.equals(o.name));
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ",name=" + name + ",memory=" + super.toString() + "]";
	}
}
