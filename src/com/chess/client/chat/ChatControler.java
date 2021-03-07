package com.chess.client.chat;

import com.chess.client.Client;
import com.chess.client.MainClient;
import com.chess.client.chat.ChatPanel.ChatType;
import com.chess.common.Account;
import com.chess.common.messages.Message;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ChatControler {
	
	private final ChatPanel panel;
	
	public ChatControler(ChatPanel panel) {
		this.panel = panel;
	}
	
	public void sendAndDisplayMessage(TextArea textToSend, Client client, Account with)
	{
		if(textToSend.getText().isEmpty())
			return;
		Message toSend = new Message(MainClient.getAccount(), textToSend.getText(), with);
		printMessage(toSend);
		clearMessage(textToSend);
		client.sendMessage(toSend);
	}
	
	public void printMessage(Message message)
	{
		if(panel.type == ChatType.PRIVATE_MESSAGE && message.getWith() != null && !message.getSender().equals(MainClient.getAccount())) {
			if(!message.getWith().equals(MainClient.getAccount())) {
				System.out.println("Not same account: " + message.getWith() + " / " + MainClient.getAccount());
				return;
			}
			if(panel.getWith() != null || !panel.getWith().equals(message.getSender())) {
				panel.setWith(message.getSender());
				panel.receivedText.getChildren().clear();
			}
		}
		Platform.runLater(() -> {
			Label text = new Label(message.toShow()+"\n");
			text.setWrapText(true);
			text.prefWidthProperty().bind(panel.receivedText.prefWidthProperty());
			//text.setPrefWidth(receivedText.getPrefWidth()-20);
			text.setLayoutX(5);
			text.setAlignment(Pos.CENTER_LEFT);
			panel.receivedText.getChildren().add(text);
		});
		
	}
	
	public void clearMessage(TextArea textToSend)
	{
		textToSend.setText("");
	}
	
	
	
}
