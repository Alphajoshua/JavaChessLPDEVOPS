package com.chess.client.account;

import com.chess.client.MainClient;
import com.chess.common.Account;
import com.chess.common.security.SHA256;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class AccountPanel extends GridPane {
	
	private final float width;
	
	public AccountPanel(float width) {
		this.width = width;
		Account acc = MainClient.getAccount();
		if(acc == null || acc.getName().equalsIgnoreCase("Not logged")) {
			// login page
			TextField userNameInput = createTextField("Nom d'utilisateur");
			
			TextField passwordInput = createTextField("Mot de passe");
			
			Button loginButton = new Button("Se connecter");
			loginButton.setAlignment(Pos.BASELINE_CENTER);
			loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() { 
				@Override
				public void handle(MouseEvent e) {
					AccountController.tryToConnect(userNameInput.getText(), SHA256.hash(passwordInput.getText(), "ceciestunsalt"));
				}
			});

			this.add(userNameInput, 0, 0);
			this.add(passwordInput, 0, 1);
			this.add(loginButton, 0, 2);
		} else {
			// account infos
			Label accountInfos = new Label(acc.getName());
			accountInfos.setWrapText(true);
			accountInfos.setAlignment(Pos.CENTER_LEFT);
			this.getChildren().add(accountInfos);
		}
	}
	
	private TextField createTextField(String field) {
		TextField text = new TextField(field);
		text.setPrefWidth(width);
		text.setAlignment(Pos.BASELINE_CENTER);
		return text;
	}
}
