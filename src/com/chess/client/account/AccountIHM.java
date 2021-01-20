package com.chess.client.account;

import com.chess.client.MainClient;
import com.chess.common.Account;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AccountIHM extends Application {

	public static final int WARP_LENGHT = 220;


	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Mon compte");

		FlowPane contextPane = new FlowPane();
		contextPane.setPadding(new Insets(5, 5, 0, 0));
		contextPane.setVgap(4);
		contextPane.setHgap(4);
		contextPane.setPrefWrapLength(170); // preferred width allows for two columns
		contextPane.setStyle("-fx-background-color: DAE6F3;");
		contextPane.setAlignment(Pos.BASELINE_CENTER);
		
		Account acc = MainClient.getAccount();
		if(acc == null) {
			// login page
			TextField userNameInput = new TextField("Nom d'utilisateur");
			userNameInput.setPrefWidth(170);
			
			TextField passwordInput = new TextField("Mot de passe");
			passwordInput.setPrefWidth(170);
			
			Button loginButton = new Button("Se connecter");
			loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() { 
				@Override
				public void handle(MouseEvent e) {
					try {
						
					} catch (Exception exc) {
						exc.printStackTrace();
					}
				}
			});
			
			contextPane.getChildren().addAll(userNameInput, passwordInput, loginButton);
		} else {
			// account infos
			
		}

		GridPane grid = new GridPane();

		grid.add(contextPane, 1, 0);

		BorderPane rootLayout = new BorderPane(grid);
		rootLayout.setPrefSize(800, 800);
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void run(String[] args) {
		launch(args);
	}

}
