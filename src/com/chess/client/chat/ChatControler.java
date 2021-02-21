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
						text.maxWidthProperty().bind(receivedText.prefWidthProperty().multiply(0.60));
						
						HBox box = new HBox();
						box.prefWidthProperty().bind(receivedText.prefWidthProperty().add(5));
						box.getChildren().add(text);
						
						box.setAlignment(Pos.BASELINE_LEFT);
						
						receivedText.getChildren().add(box);
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
				text.maxWidthProperty().bind(receivedText.prefWidthProperty().multiply(0.60));
				
				HBox box = new HBox();
				box.prefWidthProperty().bind(receivedText.prefWidthProperty().subtract(5));
				box.getChildren().add(text);
				
				box.setAlignment(Pos.BASELINE_RIGHT);
				receivedText.getChildren().add(box);
			}
		});
	}
	
	public void clearMessage(TextArea textToSend)
	{
		textToSend.setText("");
	}
	
	
	
}
