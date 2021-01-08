package com.chess.server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.chess.common.Account;
import com.chess.server.Database;

public class AccountManager {

	private static final HashMap<Long, Account> LOADED_ACCOUNT = new HashMap<>();
	
	/**
	 * Save account in database
	 * 
	 * @param acc the saved account
	 */
	public static void save(Account acc) {
		try {
			Connection co = Database.getConnection();
			PreparedStatement update = co.prepareStatement("UPDATE accounts SET name = ? WHERE id = ?");
			update.setString(1, acc.getName());
			update.setLong(2, acc.getId());
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
