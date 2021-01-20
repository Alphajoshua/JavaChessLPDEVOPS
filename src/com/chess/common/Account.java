package com.chess.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.chess.server.manager.OldGameManager;

@SuppressWarnings("serial")
public class Account implements Serializable {
	
	public static final Account SERVER_ACCOUNT = new Account(-1, "Server");
	
	private final long id;
	private String name;
	private final List<OldGame> oldGames = new ArrayList<>();
	
	/**
	 * New account just created from database
	 * 
	 * @param id the account id
	 * @param name the account name
	 */
	public Account(long id, String name) {
		this.id = id;
		this.name = name;
		load();
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
}
