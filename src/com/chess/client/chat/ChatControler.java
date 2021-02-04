package com.chess.client.chat;

import com.chess.client.Client;
import com.chess.client.MainClient;
import com.chess.common.messages.Message;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class ChatControler {
	
	
	public void sendAndDisplayMessage(TextArea textToSend, TextFlow receivedText, Client client)
	{
		Message toSend = new Message(MainClient.getAccount(), textToSend.getText());
		printSentMessage(receivedText,toSend);
		clearMessage(textToSend);
		client.sendMessage(toSend);
	}
	
	public void printMessage (TextFlow receivedText, Message message)
	{
		Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						Label text = new Label (message.toShow()+"\n");
						text.setWrapText(true);
						text.prefWidthProperty().bind(receivedText.prefWidthProperty().multiply(2f/3f));
						text.setTextAlignment(TextAlignment.LEFT);
						receivedText.getChildren().add(text);
					}
				});
		
	}
	
	public void printSentMessage (TextFlow receivedText, Message message)
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				Label text = new Label (message.toShow()+"\n");
				text.setWrapText(true);
				text.prefWidthProperty().bind(receivedText.prefWidthProperty().subtract(5));
				text.setTextAlignment(TextAlignment.RIGHT);
				text.setAlignment(Pos.CENTER_RIGHT);
				receivedText.getChildren().add(text);
			}
		});
	}
	
	public void clearMessage(TextArea textToSend)
	{
		textToSend.setText("");
	}
	
	
	
}
