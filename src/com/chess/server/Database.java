package com.chess.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static String url, user, password;
	private static Connection connection;
	private static long lastValidityCheck = 0;
	
	public static Connection getConnection() throws SQLException {
		if(!isValidConnection()) {
			connect();
		}
		return connection;
	}
	
	public static void connect() throws SQLException {
		Config conf = Config.getConfig();
		try {
			Class.forName(conf.getValue("driver", "oracle.jdbc.driver.OracleDriver"));
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load sql driver.");
			e.printStackTrace();
		}
		url = conf.getValue("url", "jdbc:oracle://127.0.0.1/database");
		user = conf.getValue("user", "root");
		password = conf.getValue("password", "password");
		connection = DriverManager.getConnection(url, user, password);
		System.out.println("Driver OK, connection successfull");
		
		Statement state = connection.createStatement();
		state.executeUpdate("CREATE TABLE IF NOT EXISTS accounts (" + 
				"    id INT(11) PRIMARY KEY AUTO_INCREMENT," + 
				"    name VARCHAR(16));");
		
	}
	
	private static boolean isValidConnection() throws SQLException {
		if(connection == null || connection.isClosed())
			return false;
		long now = System.currentTimeMillis();
		// The connection may die if not used for some time, here we check each 15 minutes
		if (now - lastValidityCheck > 900_000) {
			lastValidityCheck = now;
			return connection.isValid(1);
		}
		return true;
	}
}
