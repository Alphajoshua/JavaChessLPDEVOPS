package com.chess.client.account;

import javax.swing.JOptionPane;

import com.chess.client.MainClient;
import com.chess.client.VisualApplication;
import com.chess.common.Account;
import com.chess.common.security.SHA256;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AccountPanel extends GridPane {
	
	public static AccountPanel instance;
	
	private final float width;
	
	public AccountPanel(VisualApplication app) {
		instance = this;
		this.width = app.getWidth();
		load();
	}
	
	private int slot = 0;
	
	public void load() {	
		this.getChildren().clear();
		Account acc = MainClient.getAccount();
		if(acc == null || acc.isTemp()) {
			// login page
			
			Button loginButton = createButton("Se connecter");
			
			Button registerButton = createButton("S'inscrire");
			
			TextField userNameInput = createTextField("Nom d'utilisateur");
			
			PasswordField passwordInput = new PasswordField();
			passwordInput.setPromptText("Mot de passe");
			passwordInput.setPrefWidth(width);
			passwordInput.setOnKeyPressed((e) -> {
				if(e.getCode() == KeyCode.ENTER)
					loginButton.getOnMouseClicked().handle(null);
			});
			passwordInput.setAlignment(Pos.BASELINE_CENTER);

			// login button
			loginButton.setOnMouseClicked((e) -> {
				loginButton.setDisable(true);
				registerButton.setDisable(true);
				AccountController.tryToConnect(userNameInput.getText(), SHA256.hash(passwordInput.getText(), "ceciestunsalt"));
			});

			// register button
			registerButton.setOnMouseClicked((e) -> {
				loginButton.setDisable(true);
				registerButton.setDisable(true);
				AccountController.tryToRegister(userNameInput.getText(), SHA256.hash(passwordInput.getText(), "ceciestunsalt"));
			});
			
			// add item to grid pane
			this.add(userNameInput, 0, 0);
			this.add(passwordInput, 0, 1);
			this.add(loginButton, 0, 2);
			this.add(registerButton, 0, 3);
		} else {
			slot = 0;
			// account infos
			Label accountInfos = createLabel("Mon compte: " + acc.getName() + " (id: " + acc.getId() + ") Créé le " + acc.getCreatedAt().toString());
			this.add(accountInfos, 0, slot++);
			
			MainClient.getClient().getOnlineUsers().forEach((connected) -> {
				if(connected == acc || connected.isTemp()) // don't show itself
					return;
				Button connectedInfos = createButton("Compte: " + connected.getName() + " (id: " + connected.getId() + ")");
				connectedInfos.setOnMouseClicked((e) -> {
					connectedInfos.setDisable(true);
					final Stage dialog = new Stage();
	                dialog.initModality(Modality.NONE);
	                dialog.initOwner(VisualApplication.getApplication().getStage());
	                VBox dialogVbox = new VBox(20);
	                Label createdAt = createLabel("Compte créé le " + connected.getCreatedAt().toString());
	                
	                Button discuss = createButton("Discuter avec " + connected.getName());
	                discuss.setOnMouseClicked((ev) -> {
	                	VisualApplication.getApplication().getPrivateChatPanel().setWith(connected);
	                	dialog.close();
	                });
	                
	                Button defi = createButton("Défier " + connected.getName());
	                defi.setOnMouseClicked((ev) -> JOptionPane.showMessageDialog(null, "Prochainement !"));

	                dialogVbox.getChildren().add(createdAt);
	                dialogVbox.getChildren().add(discuss);
	                dialogVbox.getChildren().add(defi);
	                
	                Scene dialogScene = new Scene(dialogVbox, 300, 200);

	                dialog.setOnCloseRequest((ev) -> connectedInfos.setDisable(false));
	                
	                dialog.setScene(dialogScene);
	                dialog.show();
				});
				this.add(connectedInfos, 0, slot++);
			});
		}
	}
	
	/**
	 * Create a text field to write text, with all basic default options
	 * 
	 * @param field the promprt text
	 * @return a new text field
	 */
	private TextField createTextField(String field) {
		TextField text = new TextField();
		text.setPromptText(field);
		text.setPrefWidth(width);
		text.setAlignment(Pos.BASELINE_CENTER);
		return text;
	}
	
	/**
	 * Create a button with all basic default options
	 * 
	 * @param name the name of the button
	 * @return a new button object
	 */
	private Button createButton(String name) {
        Button b = new Button(name);
        b.setPrefWidth(width);
        b.setWrapText(true);
        b.setAlignment(Pos.BASELINE_CENTER);
        return b;
	}
	
	/**
	 * Create a new label with all basic default options
	 * 
	 * @param name the name of the label
	 * @return a new label object
	 */
	private Label createLabel(String name) {
		Label l = new Label(name);
        l.setPrefWidth(width);
        l.setWrapText(true);
        l.setAlignment(Pos.BASELINE_CENTER);
        return l;
	}
}
