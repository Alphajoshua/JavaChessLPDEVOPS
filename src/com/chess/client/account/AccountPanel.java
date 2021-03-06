package com.chess.client.account;

import com.chess.client.MainClient;
import com.chess.common.Account;
import com.chess.common.security.SHA256;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AccountPanel extends GridPane {
	
	public static AccountPanel instance;
	
	private final float width;
	public boolean haveToCloseConnection = true;
	
	public AccountPanel(float width) {
		instance = this;
		this.width = width;
		Account acc = MainClient.getAccount();
		if(acc == null || acc.getName().equalsIgnoreCase("Not logged")) {
			// login page
			TextField userNameInput = createTextField("Nom d'utilisateur");
			//userNameInput.enter
			
			PasswordField passwordInput = new PasswordField();
			passwordInput.setPromptText("Mot de passe");
			passwordInput.setPrefWidth(width);
			passwordInput.setAlignment(Pos.BASELINE_CENTER);
			
			Button loginButton = new Button("Se connecter");
			loginButton.setAlignment(Pos.BASELINE_CENTER);
			loginButton.setPrefWidth(width);
			loginButton.setOnMouseClicked((e) -> AccountController.tryToConnect(userNameInput.getText(), SHA256.hash(passwordInput.getText(), "ceciestunsalt")));

			
			Button registerButton = new Button("S'inscrire");
			registerButton.setAlignment(Pos.BASELINE_CENTER);
			registerButton.setPrefWidth(width);
			registerButton.setOnMouseClicked((e) -> AccountController.tryToRegister(userNameInput.getText(), SHA256.hash(passwordInput.getText(), "ceciestunsalt")));

			this.add(userNameInput, 0, 0);
			this.add(passwordInput, 0, 1);
			this.add(loginButton, 0, 2);
			this.add(registerButton, 0, 3);
		} else {
			// account infos
			Label accountInfos = new Label(acc.getName());
			accountInfos.setWrapText(true);
			accountInfos.setAlignment(Pos.CENTER_LEFT);
			this.getChildren().add(accountInfos);
		}
	}
	
	private TextField createTextField(String field) {
		TextField text = new TextField();
		text.setPromptText(field);
		text.setPrefWidth(width);
		text.setAlignment(Pos.BASELINE_CENTER);
		return text;
	}
}
