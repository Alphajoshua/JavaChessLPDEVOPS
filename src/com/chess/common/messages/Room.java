package com.chess.common.messages;
import java.util.ArrayList;
import java.util.List;

public class Room {

	private List<String> interlocutor;
	private List<Message> listSentMessages;
	
	public Room(String firstInterlocutor,String secondInterlocutor)
	{
		interlocutor = new ArrayList<String>();
		listSentMessages = new ArrayList<Message>();
		
		interlocutor.add(firstInterlocutor);
		interlocutor.add(secondInterlocutor);
	}
	
	public void addInterlocutor (String name)
	{
		interlocutor.add(name);
	}
	
	public void deleteInterlocutor (String name)
	{
		interlocutor.remove(name);
	}
	
	public String getLastMessage()
	{
		if(listSentMessages.size()>0)
			return listSentMessages.get(0).getMessage();
		else
			return "";
	}
	
	public String getLastSender()
	{
		if(listSentMessages.size()>0)
			return listSentMessages.get(0).getName();
		else
			return "";
	}
	
	void addMessage(Message newMessage)
	{
		listSentMessages.add(0,newMessage);
	}
	
}
