package com.chess.client.account;

import javax.swing.JOptionPane;

import com.chess.client.MainClient;
import com.chess.client.chat.ChatIHM;
import com.chess.common.messages.login.Login;
import com.chess.common.messages.login.LoginResult;
import com.chess.common.messages.login.LoginResult.LoginResultType;
import com.chess.common.messages.login.Register;
import com.chess.common.messages.login.RegisterResult;
import com.chess.common.messages.login.RegisterResult.RegisterResultType;

import javafx.application.Platform;
import javafx.stage.Stage;

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
			AccountPanel.instance.haveToCloseConnection = false;
			Platform.exit();
			Platform.runLater(() -> {
				try {
					new ChatIHM().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			//Platform.runLater(() -> ChatIHM.main(new String[0]));
			//ChatIHM.main(new String[0]);
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
			AccountPanel.instance.haveToCloseConnection = false;
			Platform.exit();
			ChatIHM.main(new String[0]);
		} else {
			JOptionPane.showMessageDialog(null, "Aie ... " + type.getMessage());
		}
	}
}
