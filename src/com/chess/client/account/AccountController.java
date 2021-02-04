package com.chess.client.account;

import javax.swing.JOptionPane;

import com.chess.client.MainClient;
import com.chess.common.messages.login.Login;
import com.chess.common.messages.login.LoginResult;
import com.chess.common.messages.login.LoginResult.LoginResultType;
import com.chess.common.messages.login.Register;

public class AccountController {

	private static Login login;
	
	public static void tryToConnect(String name, String hashedPassword) {
		if(name == null || hashedPassword == null || name.isEmpty() || hashedPassword.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vous devez renseigner les champs avant d'essayer de vous connecter.");
			return;
		}
		login = new Login(name, hashedPassword);
		MainClient.getClient().sendMessage(login);
	}
	
	public static void manageConnect(LoginResult login) {
		LoginResultType type = login.getLoginResult();
		if(type.isSuccess()) {
			MainClient.setAccount(login.getLoggedAccount());
			JOptionPane.showMessageDialog(null, type.getMessage());
		} else {
			JOptionPane.showMessageDialog(null, "Aie ... " + type.getMessage());
		}
	}
	
	public static void manageRegister(Register login) {
		
	}
}
