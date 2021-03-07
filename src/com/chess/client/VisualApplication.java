package com.chess.client;

import static com.chess.client.MainClient.client;

import com.chess.client.account.AccountPanel;
import com.chess.client.chat.ChatPanel;
import com.chess.client.chat.ChatPanel.ChatType;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VisualApplication extends Application {

	public static VisualApplication app;
	public static VisualApplication getApplication() {
		return app;
	}
	public static void run(String[] args) {
		Application.launch(VisualApplication.class, args);
	}
	
	public static final int WARP_LENGHT = 220;

	public static final Color BACKGROUND_COLOR = Color.rgb(76,119,153,1.0);
	
	private float width = 400;
	private float height = 500;
	private Stage stage;
	private GridPane panel;
	private ChatPanel generalChatPanel, privateChatPanel;
	private AccountPanel accountPanel;

	public VisualApplication() {
		VisualApplication.app = this;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		
		primaryStage.setMinHeight(getHeight());
		primaryStage.setMinWidth(getWidth());
		GridPane pane = new GridPane();

		pane.add(generalChatPanel = new ChatPanel(this, ChatType.GENERAL), 0, 0);
		pane.add(privateChatPanel = new ChatPanel(this, ChatType.PRIVATE_MESSAGE), 1, 0);
		pane.add(accountPanel = new AccountPanel(this), 2, 0);
		
		Scene scene = new Scene(pane, app.getWidth() * 3, app.getHeight());
		scene.setFill(VisualApplication.BACKGROUND_COLOR);
		primaryStage.setTitle("Chess");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
		
	public void setTitle(String name) {
		this.stage.setTitle(name);
	}
	
	public void setScene(Scene scene) {
		this.stage.setScene(scene);
	}
	
	@Override
	public void stop() throws Exception {
		client.disconnectServer();
		System.exit(0);
	}

	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public GridPane getPanel() {
		return panel;
	}
	
	public AccountPanel getAccountPanel() {
		return accountPanel;
	}
	
	public ChatPanel getGeneralChatPanel() {
		return generalChatPanel;
	}
	
	public ChatPanel getPrivateChatPanel() {
		return privateChatPanel;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void updateAll() {
		getAccountPanel().load();
		getGeneralChatPanel().update();
		getPrivateChatPanel().update();
	}
}
