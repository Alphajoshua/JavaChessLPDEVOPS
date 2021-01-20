package com.chess.client.chat;

import javafx.application.Application;

import static com.chess.client.MainClient.client;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChatIHM extends Application {
		
	String roomName = "Chatroom for test";
	Color backgroundColor = Color.rgb(76,119,153,1.0);
	Stage roomStage;
	
	float width = 400;
	float height = 500;

	@Override
	public void start(Stage chatStage) throws Exception{
		
		
		chatStage.setMinHeight(500);
		chatStage.setMinWidth(400);

		ChatPanel chatPanel = new ChatPanel(width,height);
		client.setView(chatPanel);
		
		Group chatGroup = new Group(); 
		
		chatGroup.getChildren().add(chatPanel);
		
		Scene chatScene = new Scene(chatGroup,width,height);
		chatScene.setFill(backgroundColor);
		
		chatStage.setTitle(roomName);
		
		chatStage.setScene(chatScene);
		
		chatScene.widthProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal.floatValue()<200)
				newVal=oldVal;
			chatPanel.updateWidth(newVal);
		});

		chatScene.heightProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal.floatValue()<200)
				newVal=oldVal;
			chatPanel.updateHeight(newVal);
		});
		
		chatStage.show();
	}

	@Override
	public void stop() throws Exception {
		client.disconnectServer();
		System.exit(0);
	}
	
	public static void main( String[] args) {
		Application.launch(ChatIHM.class,args);
	}
}