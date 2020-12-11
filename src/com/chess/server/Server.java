package com.chess.server;

import java.util.ArrayList;
import java.util.List;

import com.chess.common.SendableMessage;
import com.chess.common.StatusUpdate;
import com.chess.common.StatusUpdate.StatusType;

public class Server {

	private final List<ConnectedClient> clients = new ArrayList<>();
	private final int port;
	
	/**
	 * Create a new server and a new thread which manage {@link Connection}
	 * 
	 * @param port the server port
	 */
	public Server(int port) {
		this.port = port;
		
		new Thread(new Connection(this)).start();
	}
	
	/**
	 * Add a client to the server
	 * And broadcast it to everyone
	 * 
	 * @param client the client to add
	 */
	public void addClient(ConnectedClient client) {
		clients.add(client);
		broadcastMessage(new StatusUpdate(StatusType.LOGIN, client.getId(), client.getName()), client.getId());
	}
	
	/**
	 * Remove a client from the server
	 * And broadcast it to everyone
	 * 
	 * @param client the client to remove
	 */
	public void disconnectClient(ConnectedClient client) {
		client.closeClient();
		clients.remove(client);
		broadcastMessage(new StatusUpdate(StatusType.LOGOUT, client.getId(), client.getName()), client.getId());
	}
	
	/**
	 * Broadcast a message except to the given ID
	 * 
	 * @param m the message to send
	 * @param from the sender of the message
	 */
	public void broadcastMessage(SendableMessage m, int from) {
		clients.forEach((cc) -> {
			if(cc.getId() != from) {
				cc.sendMessage(m);
			}
		});
	}
	
	/**
	 * Get all connected clients 
	 * 
	 * @return connected clients
	 */
	public List<ConnectedClient> getClients() {
		return clients;
	}
	
	/**
	 * Get server port
	 * 
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
}
