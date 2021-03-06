package com.chess.server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.chess.common.Account;
import com.chess.common.messages.login.LoginResult.LoginResultType;
import com.chess.common.security.SHA256;
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
					return getAccount(rs);
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
	public static Object getAccount(String name, String passwd) {
		try {
			Connection co = Database.getConnection();
			PreparedStatement select = co.prepareStatement("SELECT * FROM accounts WHERE name = ?");
			select.setString(1, name);
			ResultSet rs = select.executeQuery();
			if(rs.next()) {
				if(SHA256.comparePassword(rs.getString("password"), passwd))
					return getAccount(rs);
				return LoginResultType.WRONG_PASSWORD;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LoginResultType.UNKNOW_ACCOUNT;
	}
	
	private static Account getAccount(ResultSet rs) throws SQLException {
		return new Account(rs.getLong("id"), rs.getString("name"));
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
				return createNewAccount(name, "");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Account createNewAccount(String name, String password) {
		try {
			Connection co = Database.getConnection();
			PreparedStatement insertStm = co.prepareStatement("INSERT INTO accounts(name, password) VALUES (?, ?);", new String[] {"id"});
			insertStm.setString(1, name);
			insertStm.setString(2, password);
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
