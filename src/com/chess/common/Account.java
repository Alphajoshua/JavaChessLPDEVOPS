package com.chess.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chess.server.Database;

public class Account {

	private final long id;
	private String name;
	
	/**
	 * Load account from database
	 * 
	 * @param id the account id
	 */
	public Account(long id) {
		this.id = id;
		
	}
	
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
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Save account in database
	 */
	public void save() {
		try {
			Connection co = Database.getConnection();
			PreparedStatement update = co.prepareStatement("UPDATE accounts SET name = ? WHERE id = ?");
			update.setString(1, getName());
			update.setLong(2, getId());
			update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Account createNewAccount(String name) {
		try {
			Connection co = Database.getConnection();
			PreparedStatement insertStm = co.prepareStatement("INSERT INTO accounts(name) VALUES (?);", new String[] {"id"});
			insertStm.setString(1, name);
			int affectedRows = insertStm.executeUpdate();

			if (affectedRows > 0) {
				ResultSet rs = insertStm.getGeneratedKeys();
			    if (rs.next())
					return new Account(rs.getLong(1), name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
