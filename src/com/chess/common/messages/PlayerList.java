package com.chess.common.messages;

import java.util.List;

import com.chess.common.Account;

/**
 * This packet is used to get full player list.
 * The client can send whatever he was in the account list because the server will completly ignore it
 * 
 * The server will just send the list of all connected client
 * 
 * @author Elikill58
 */
@SuppressWarnings("serial")
public class PlayerList  extends ServerMessage {

	private final List<Account> all;
	
	public PlayerList(List<Account> all) {
		super(Account.SERVER_ACCOUNT);
		this.all = all;
	}
	
	/**
	 * Get all connected client
	 * 
	 * @return all connected client
	 */
	public List<Account> getAll() {
		return all;
	}

}
