package com.chess.client.chat;

import static com.chess.client.MainClient.client;

import com.chess.client.MainClient;
import com.chess.client.VisualApplication;
import com.chess.common.Account;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

public class ChatPanel extends Parent {

	ChatControler chatCTRL;
	int padding = 5;
	//Text input to send
	TextArea textToSend;
	
	//Message flow display area
	ScrollPane scrollReceivedText;
	TextFlow receivedText;
	
	//Buttons / event trigger
	Button sendBtn;
	Button clearBtn;
	
	Label chatTypeInformation;
	
	float width;
	float height;
	
	ChatType type;
	Account with;
	
	public ChatControler getChatCTRL()
	{
		return this.chatCTRL;
	}

	public TextFlow getReceivedText()
	{
		return this.receivedText;
	}
	
	public ChatPanel (VisualApplication app, ChatType type)
	{
		this.chatCTRL = new ChatControler(this);
		
		this.width = app.getWidth();
		this.height = app.getHeight();
		//Init each elements
		this.textToSend = new TextArea();
		this.scrollReceivedText = new ScrollPane();
		this.receivedText = new TextFlow();
		this.sendBtn = new Button();
		this.clearBtn = new Button();
		this.chatTypeInformation = new Label();
		
		this.type = type;
		
		update();
	}
	
	public void update() {
		this.getChildren().clear();
		
		if(MainClient.getAccount() == null || MainClient.getAccount().isTemp()) {
			Label l = new Label("Vous devez être connecté pour parler !");
	        l.setPrefWidth(width);
	        l.setPrefHeight(height);
	        l.setWrapText(true);
	        l.setAlignment(Pos.BASELINE_CENTER);
			this.getChildren().add(l);
			return;
		}
		
		updateDisplay();

		//Add each element to the object
		if(!(type.equals(ChatType.PRIVATE_MESSAGE) && with == null)) {
			this.getChildren().add(textToSend);
			this.getChildren().add(scrollReceivedText);
			this.getChildren().add(sendBtn);
			this.getChildren().add(clearBtn);
		}
		this.getChildren().add(chatTypeInformation);
	}
	
	public void updateHeight(Number newVal)
	{
		this.height = newVal.floatValue();
		updateDisplay();
	}
	
	public void updateWidth(Number newVal)
	{
		this.width = newVal.floatValue();
		updateDisplay();
	}
	
	public void updateDisplay()
	{
		//Set position for each element
		
		chatTypeInformation.setText(type.getName().replaceAll("%name%", with == null ? "Personne" : with.getName()));
		chatTypeInformation.setLayoutX(padding);
		chatTypeInformation.setLayoutY(0);
		chatTypeInformation.setPrefWidth(width);
		chatTypeInformation.setMinHeight(15);
		
		scrollReceivedText.setLayoutX(padding);
		scrollReceivedText.setLayoutY(padding + 15);
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
		textToSend.setWrapText(true);
		
		sendBtn.setLayoutX(textToSend.getPrefWidth()+(2*padding));
		sendBtn.setLayoutY(textToSend.getLayoutY());
		sendBtn.setPrefWidth(width - textToSend.getPrefWidth() - (3*padding));
		sendBtn.setPrefHeight( (textToSend.getPrefHeight()-padding)/2 );
		sendBtn.setVisible(true);
		sendBtn.setText("Send");
		sendBtn.setOnAction((e) -> chatCTRL.sendAndDisplayMessage(textToSend, client, with));
		
		
		clearBtn.setLayoutX(textToSend.getPrefWidth()+(2*padding) );
		clearBtn.setLayoutY(sendBtn.getLayoutY()+sendBtn.getPrefHeight()+padding);
		clearBtn.setPrefWidth(width - textToSend.getPrefWidth() - (3*padding));
		clearBtn.setPrefHeight((textToSend.getPrefHeight()-padding)/2);
		clearBtn.setVisible(true);
		clearBtn.setText("Clear");
		clearBtn.setOnAction((e) -> chatCTRL.clearMessage(textToSend));
	}
	
	public Account getWith() {
		return with;
	}
	
	public void setWith(Account with) {
		this.with = with;
		Platform.runLater(this::update);
	}
	
	public static enum ChatType {
		GENERAL("Général"), PRIVATE_MESSAGE("Message privée avec %name%");
		
		private final String name;
		
		private ChatType(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
}
