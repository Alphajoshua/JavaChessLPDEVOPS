package com.chess.client.account;

import javax.swing.JOptionPane;

import com.chess.client.MainClient;
import com.chess.client.VisualApplication;
import com.chess.common.messages.PlayerList;
import com.chess.common.messages.login.Login;
import com.chess.common.messages.login.LoginResult;
import com.chess.common.messages.login.LoginResult.LoginResultType;
import com.chess.common.messages.login.Register;
import com.chess.common.messages.login.RegisterResult;
import com.chess.common.messages.login.RegisterResult.RegisterResultType;

import javafx.application.Platform;

public class AccountController {
	
	public static void tryToConnect(String name, String hashedPassword) {
		if(name == null || hashedPassword == null || name.isEmpty() || hashedPassword.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vous devez renseigner les champs avant d'essayer de vous connecter.");
			return;
		}
		Login login = new Login(name, hashedPassword);
		MainClient.getClient().sendMessage(login);
	}
	
	public static void manageConnect(LoginResult login) {
		LoginResultType type = login.getLoginResult();
		if(type.isSuccess()) {
			MainClient.setAccount(login.getLoggedAccount());
			JOptionPane.showMessageDialog(null, type.getMessage());
			MainClient.getClient().sendMessage(new PlayerList(null));
			Platform.runLater(() -> VisualApplication.getApplication().updateAll());
		} else {
			JOptionPane.showMessageDialog(null, "Aie ... " + type.getMessage());
		}
	}
	
	public static void tryToRegister(String name, String hashedPassword) {
		if(name == null || hashedPassword == null || name.isEmpty() || hashedPassword.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vous devez renseigner les champs avant d'essayer de vous inscrire.");
			return;
		}
		Register register = new Register(name, hashedPassword);
		MainClient.getClient().sendMessage(register);
	}

	public static void manageRegister(RegisterResult register) {
		RegisterResultType type = register.getRegisterResult();
		if(type.isSuccess()) {
			MainClient.setAccount(register.getRegisteredAccount());
			JOptionPane.showMessageDialog(null, type.getMessage());
			MainClient.getClient().sendMessage(new PlayerList(null));
			Platform.runLater(() -> VisualApplication.getApplication().updateAll());
		} else {
			JOptionPane.showMessageDialog(null, "Aie ... " + type.getMessage());
		}
	}
}
