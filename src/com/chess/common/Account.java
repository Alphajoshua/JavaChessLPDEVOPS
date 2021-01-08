package com.chess.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Account implements Serializable {
	
	private final long id;
	private String name;
	
	/**
	 * New account just created from database
	 * 
	 * @param id the account id
	 * @param name the account name
	 */
	public Account(long id, String name) {
		this.id = id;
		this.name = name;
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
