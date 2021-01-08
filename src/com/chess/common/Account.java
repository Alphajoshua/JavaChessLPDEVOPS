package com.chess.common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.chess.server.Database;

@SuppressWarnings("serial")
public class Account implements Serializable {

	private static final HashMap<Long, Account> LOADED_ACCOUNT = new HashMap<>();
	
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
	
	/**
	 * Get a user account by it's ID
	 * 
	 * @param id the user ID
	 * @return an account or null if cannot found account
	 */
	public static Account getAccount(long id) {
		return LOADED_ACCOUNT.computeIfAbsent(id, (idd) -> {
			try {
				Connection co = Database.getConnection();
				PreparedStatement select = co.prepareStatement("SELECT * FROM accounts WHERE id = ?");
				select.setLong(1, id);
				ResultSet rs = select.executeQuery();
				if(rs.next()) {
					return new Account(id, rs.getString("name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
	
	/**
	 * Get or create a user account by it's name
	 * 
	 * @param name the user name
	 * @return an account or null if cannot create account
	 */
	public static Account getAccount(String name) {
		try {
			Connection co = Database.getConnection();
			PreparedStatement select = co.prepareStatement("SELECT * FROM accounts WHERE name = ?");
			select.setString(1, name);
			ResultSet rs = select.executeQuery();
			if(rs.next()) {
				return getAccount(rs.getLong("id"));
			} else {
				return createNewAccount(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
