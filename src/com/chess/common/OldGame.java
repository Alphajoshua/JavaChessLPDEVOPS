package com.chess.common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chess.server.Database;

@SuppressWarnings("serial")
public class OldGame implements Serializable {

	private final long id;
	private final long blackUserId, whiteUserId, winnerId;
	
	public OldGame(long id) throws SQLException {
		this.id = id;
		
		Connection co = Database.getConnection();
		PreparedStatement ps = co.prepareStatement("SELECT * FROM old_games WHERE id = ?");
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			this.blackUserId = rs.getLong("user_black");
			this.whiteUserId = rs.getLong("user_white");
			this.winnerId = rs.getLong("winner");
		} else {
			this.blackUserId = 0;
			this.whiteUserId = 0;
			this.winnerId = 0;
		}
	}
	
	public long getId() {
		return id;
	}
	
	public long getBlackUserId() {
		return blackUserId;
	}
	
	public Account getBlackUser() {
		return Account.getAccount(blackUserId);
	}
	
	public long getWhiteUserId() {
		return whiteUserId;
	}
	
	public Account getWhiteUser() {
		return Account.getAccount(whiteUserId);
	}
	
	public long getWinnerId() {
		return winnerId;
	}
	
	public Account getWinner() {
		return Account.getAccount(winnerId);
	}
}
