package com.chess.common;

import java.sql.Connection;
import java.sql.SQLException;

import com.chess.server.Database;

public class Account {

	private final int id;
	private String name;
	
	public Account(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void save() {
		try {
			Connection co = Database.getConnection();
			co.createStatement().executeQuery("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createNewAccount(String name) {
		try {
			Connection co = Database.getConnection();
			co.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
