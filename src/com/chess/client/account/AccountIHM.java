package com.chess.client.account;

import static com.chess.client.MainClient.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AccountIHM extends Application {

	public static final int WARP_LENGHT = 220;

	private static final Color BACKGROUND_COLOR = Color.rgb(76, 119, 153, 1.0);

	private float width = 400;
	private float height = 500;

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setMinHeight(height);
		primaryStage.setMinWidth(width);
		GridPane chatGroup = new GridPane();

		// chatGroup.getChildren().add(panel = new AccountPanel(width));

		Scene chatScene = new Scene(chatGroup, width, height);
		chatScene.setFill(BACKGROUND_COLOR);

		primaryStage.setTitle("Mon compte");
		primaryStage.setScene(chatScene);
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		client.disconnectServer();
		System.exit(0);
	}

	public static void main(String[] args) {
		Application.launch(AccountIHM.class, args);
	}
}
