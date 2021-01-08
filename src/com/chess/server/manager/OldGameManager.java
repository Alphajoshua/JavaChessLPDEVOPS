package com.chess.server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chess.common.Account;
import com.chess.common.OldGame;
import com.chess.server.Database;

public class OldGameManager {

	
	public static OldGame loadGame(long id) {
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
	}
	
	public static void loadAllGamesOf(long accountId) {
		
	}
}
