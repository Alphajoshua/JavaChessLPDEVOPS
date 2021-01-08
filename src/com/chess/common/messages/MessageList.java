package com.chess.common.messages;
import java.util.ArrayList;
import java.util.List;

public class MessageList {

	
	private List<Message> listSentMessages= new ArrayList<Message>();
	
	
	public String getLastMessage()
	{
		if(listSentMessages.size()>0)
			return listSentMessages.get(listSentMessages.size()-1).getMessage();
		else
			return "";
	}
	
	public String getLastSender()
	{
		if(listSentMessages.size()>0)
			return listSentMessages.get(listSentMessages.size()-1).getName();
		else
			return "";
	}
	
	void addMessage(Message newMessage)
	{
		//Max size 50 messages, FIFO format
		if(listSentMessages.size()>50)
		{
			listSentMessages.remove(50);
		}
		listSentMessages.add(0,newMessage);
	}
}
