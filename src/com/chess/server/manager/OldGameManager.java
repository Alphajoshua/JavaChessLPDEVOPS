package com.chess.server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.chess.common.Account;
import com.chess.common.OldGame;
import com.chess.server.Database;

public class OldGameManager {

	private static final HashMap<Long, OldGame> GAMES_BY_ID = new HashMap<>();
	
	/**
	 * Get an old game by it's ID
	 * 
	 * @param id the old game ID
	 * @return the loaded old game or null 
	 */
	public static OldGame getOldGameById(long id) {
		return GAMES_BY_ID.computeIfAbsent(id, (idd) -> {
			try {
				Connection co = Database.getConnection();
				PreparedStatement ps = co.prepareStatement("SELECT * FROM old_games WHERE id = ?");
				ps.setLong(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					Account blackUser = AccountManager.getAccount(rs.getLong("user_black"));
					Account whiteUser = AccountManager.getAccount(rs.getLong("user_white"));
					Account winner = AccountManager.getAccount(rs.getLong("winner"));
					return new OldGame(id, blackUser, whiteUser, winner);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		});
	}
	
	/**
	 * Load and return all old game for the given account id
	 * 
	 * @param accountId the ID of the account
	 * @return all old game of account
	 */
	public static List<OldGame> loadAllGamesOf(long accountId) {
		List<OldGame> list = new ArrayList<OldGame>();
		try {
			Connection co = Database.getConnection();
			PreparedStatement ps = co.prepareStatement("SELECT * FROM old_games WHERE user_black = ? OR user_white = ?");
			ps.setLong(1, accountId);
			ps.setLong(2, accountId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				list.add(GAMES_BY_ID.computeIfAbsent(rs.getLong("id"), (id) -> buildOldGame(id, rs)));
				list.add(getOldGameById(accountId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static OldGame buildOldGame(long id, ResultSet rs) {
		try {
			Account blackUser = AccountManager.getAccount(rs.getLong("user_black"));
			Account whiteUser = AccountManager.getAccount(rs.getLong("user_white"));
			Account winner = AccountManager.getAccount(rs.getLong("winner"));
			return new OldGame(id, blackUser, whiteUser, winner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
