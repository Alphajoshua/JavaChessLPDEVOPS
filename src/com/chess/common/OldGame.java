package com.chess.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OldGame implements Serializable {

	private final long id;
	private final Account blackUser, whiteUser, winner;
	
	public OldGame(long id, Account blackUser, Account whiteUser, Account winner) {
		this.id = id;
		this.blackUser = blackUser;
		this.whiteUser = whiteUser;
		this.winner = winner;
	}
	
	public long getId() {
		return id;
	}
	
	public Account getBlackUser() {
		return blackUser;
	}
	
	public Account getWhiteUser() {
		return whiteUser;
	}
	
	public Account getWinner() {
		return winner;
	}
}
