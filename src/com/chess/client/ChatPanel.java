package com.chess.client;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

public class ChatPanel extends Parent{

	int padding = 5;
	//Text input to send
	TextArea textToSend;
	
	//Message flow display area
	ScrollPane scrollReceivedText;
	TextFlow receivedText;
	
	//Buttons / event trigger
	Button sendBtn;
	Button clearBtn;
	
	float width;
	float height;
	
	public ChatPanel (float widthAvailable, float heightAvailable)
	{
		width=widthAvailable;
		height=heightAvailable;
		//Init each elements
		textToSend = new TextArea();
		scrollReceivedText = new ScrollPane();
		receivedText = new TextFlow();
		sendBtn = new Button();
		clearBtn = new Button();
		
		updateDisplay();

		
		
		
		//Add each element to the object
		this.getChildren().add(textToSend);
		this.getChildren().add(scrollReceivedText);
		this.getChildren().add(sendBtn);
		this.getChildren().add(clearBtn);
		
		
		
	}
	
	public void updateHeight(Number newVal)
	{
		this.height= newVal.floatValue();
		updateDisplay();
	}
	
	public void updateWidth(Number newVal)
	{
		this.width=newVal.floatValue();
		updateDisplay();
	}
	
	public void updateDisplay()
	{
		//Set position for each element
		scrollReceivedText.setLayoutX(padding);
		scrollReceivedText.setLayoutY(padding);
		scrollReceivedText.setPrefWidth(width-2*padding);
		scrollReceivedText.setPrefHeight(height/6*5);
		
		receivedText.setPrefWidth(scrollReceivedText.getPrefWidth());
		receivedText.setVisible(true);

		scrollReceivedText.setContent(receivedText);
		scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
		
		textToSend.setLayoutX(padding);
		textToSend.setLayoutY(scrollReceivedText.getLayoutY()+scrollReceivedText.getPrefHeight()+padding);
		textToSend.setPrefWidth((width-(2*padding))/5*4);
		textToSend.setPrefHeight(height-textToSend.getLayoutY()-padding);
		
		sendBtn.setLayoutX(textToSend.getPrefWidth()+(2*padding));
		sendBtn.setLayoutY(textToSend.getLayoutY());
		sendBtn.setPrefWidth(width - textToSend.getPrefWidth() - (3*padding));
		sendBtn.setPrefHeight( (textToSend.getPrefHeight()-padding)/2 );
		sendBtn.setVisible(true);
		sendBtn.setText("Send");
		
		
		clearBtn.setLayoutX(textToSend.getPrefWidth()+(2*padding) );
		clearBtn.setLayoutY(sendBtn.getLayoutY()+sendBtn.getPrefHeight()+padding);
		clearBtn.setPrefWidth(width - textToSend.getPrefWidth() - (3*padding));
		clearBtn.setPrefHeight((textToSend.getPrefHeight()-padding)/2);
		clearBtn.setVisible(true);
		clearBtn.setText("Clear");
	}
}
