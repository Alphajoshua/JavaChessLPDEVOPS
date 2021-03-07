package com.chess.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.stream.Collectors;

import com.chess.common.Account;
import com.chess.common.messages.PlayerList;
import com.chess.common.messages.SendableMessage;
import com.chess.common.messages.ServerMessage;
import com.chess.common.messages.StatusUpdate;
import com.chess.common.messages.StatusUpdate.StatusType;
import com.chess.common.messages.login.Login;
import com.chess.common.messages.login.LoginResult;
import com.chess.common.messages.login.LoginResult.LoginResultType;
import com.chess.common.messages.login.Register;
import com.chess.common.messages.login.RegisterResult;
import com.chess.common.messages.login.RegisterResult.RegisterResultType;
import com.chess.server.manager.AccountManager;

public class ConnectedClient implements Runnable {

	public static int idCounter = 0;
	private final int id;
	private final Socket socket;
	private final Server server;
	private Account account;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private boolean wantToReceiveConnection = false;
	
	/**
	 * Create a new connected client
	 * 
	 * @param server the server which manage this client
	 * @param socket the client socket
	 */
	public ConnectedClient(Server server, Socket socket) {
		this.id = (idCounter++);
		this.account = new Account();
		this.socket = socket;
		this.server = server;
		try {
			this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("New client with ID: " + id);
		/*new Thread(() -> { // send a connection message for client which connect previously
			for(ConnectedClient other : new ArrayList<>(server.getClients()))
				sendMessage(new StatusUpdate(StatusType.LOGIN, other.getAccount()));
		}).start();*/
		server.addClient(this);
	}
	
	/**
	 * Get the account of the connected client
	 * 
	 * @return the client account
	 */
	public Account getAccount() {
		return account;
	}
	
	/**
	 * Set the new client of the current connection
	 * 
	 * @param account the new account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Override
	public void run() {
		while(socket.isConnected()) {
			try {
				if(in == null) {
					System.out.println("Failed to get input stream.");
					server.disconnectClient(this);
					return;
				}
				SendableMessage m = (SendableMessage) in.readObject();
				if(m == null) {
					server.disconnectClient(this);
					return;
				}
				/*if(m.getSender() != null && m.getSender() != Account.SERVER_ACCOUNT)
					this.account = m.getSender();*/
				if(m instanceof ServerMessage)
					manageMessage((ServerMessage) m);
				else
					server.broadcastMessage(m);
			} catch (EOFException e) {
				server.disconnectClient(this);
				System.out.println("User " + id +  " disconnected.");
				break;
			} catch (SocketException e) {
				server.disconnectClient(this);
				System.out.println("User " + id +  " just disconnect.");
				return;
			} catch (Exception e) {
				server.disconnectClient(this);
				e.printStackTrace();
				System.exit(0);
				return;
			}
		}
		System.out.println("Socket ended");
		server.disconnectClient(this);
	}
	
	public void manageMessage(ServerMessage srv) {
		if(srv instanceof Login) {
			Login login = (Login) srv;
			Object obj = AccountManager.getAccount(login.getLogin(), login.getPassword());
			System.out.println("Login acc: " + obj);
			if(obj instanceof Account) {
				setAccount((Account) obj);
				sendMessage(new LoginResult(LoginResultType.LOGIN_SUCCESS, account));
			} else if(obj instanceof LoginResultType) {
				sendMessage(new LoginResult((LoginResultType) obj, null));
			} else {
				sendMessage(new LoginResult(LoginResultType.UNKNOW_REQUEST, null));
			}
		} else if(srv instanceof Register) {
			Register register = (Register) srv;
			Account account = AccountManager.createNewAccount(register.getLogin(), register.getPassword());
			if(account == null)
				sendMessage(new RegisterResult(RegisterResultType.INTERNAL_ERROR, null));
			else {
				setAccount(account);
				sendMessage(new RegisterResult(RegisterResultType.REGISTER_SUCCESS, account));
			}
		} else if(srv instanceof PlayerList) {
			server.broadcastMessage(new StatusUpdate(StatusType.LOGIN, getAccount()));
			wantToReceiveConnection = true;
			sendMessage(new PlayerList(server.getClients().stream().map(ConnectedClient::getAccount).filter(Objects::nonNull).collect(Collectors.toList())));
		} else {
			System.out.println("Unknow server packet " + srv.getClass().getCanonicalName() + " from " + srv.getSender());
		}
	}

	/**
	 * Get the new client ID
	 * 
	 * @return client ID
	 */
	public int getId() {
		return id;
	}
	
	public boolean isWantToReceiveConnection() {
		return wantToReceiveConnection;
	}
	
	public boolean canBeShowned() {
		return account != null && !account.isTemp();
	}
	
	/**
	 * Send a message to the client
	 * 
	 * @param m the message to send
	 */
	public void sendMessage(SendableMessage m) {
		if(m == null) {
			System.out.println("Trying to send null message ... Abort");
			return;
		}
		try {
			out.writeObject(m);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close all clients connection
	 * (in/out/socket)
	 */
	public void closeClient() {
		try {
			if(in != null)
				in.close();
			if(out != null)
				out.close();
			if(socket != null)
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
