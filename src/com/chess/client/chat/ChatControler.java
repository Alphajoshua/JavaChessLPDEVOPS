package com.chess.client.chat;

import com.chess.client.Client;
import com.chess.common.messages.Message;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

public class ChatControler {
	
	
	public void sendAndDisplayMessage(TextArea textToSend, TextFlow receivedText, Client client)
	{
		Message toSend = new Message(0,textToSend.getText());
		printMessage(receivedText,toSend);
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
						text.prefWidthProperty().bind(receivedText.prefWidthProperty());
						//text.setPrefWidth(receivedText.getPrefWidth()-20);
						text.setLayoutX(5);
						text.setAlignment(Pos.CENTER_LEFT);
						receivedText.getChildren().add(text);
					}
				});
		
	}
	
	public void clearMessage(TextArea textToSend)
	{
		textToSend.setText("");
	}
	
	
	
}
