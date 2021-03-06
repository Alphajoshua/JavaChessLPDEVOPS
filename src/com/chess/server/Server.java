package com.chess.server;

import java.util.ArrayList;
import java.util.List;

import com.chess.common.messages.Message;
import com.chess.common.messages.SendableMessage;
import com.chess.common.messages.StatusUpdate;
import com.chess.common.messages.StatusUpdate.StatusType;

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
		broadcastMessage(new StatusUpdate(StatusType.LOGOUT, client.getAccount()));
	}
	
	/**
	 * Broadcast a message except to the given ID
	 * 
	 * @param m the message to send
	 * @param from the sender of the message
	 */
	public void broadcastMessage(SendableMessage m) {
		clients.stream().filter((cc) -> m.getSender() != null && cc.getId() != m.getSender().getId()).forEach((cc) -> {
			if(m instanceof Message) {
				Message msg = (Message) m;
				if(msg.getWith() == null || (msg.getWith() == cc.getAccount() || msg.getSender() == cc.getAccount()))
					cc.sendMessage(m);
			} else
				cc.sendMessage(m);
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
