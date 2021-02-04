package com.chess.common.messages;
import java.util.ArrayList;
import java.util.List;

import com.chess.common.Account;

public class Room {

	private List<Account> interlocutors;
	private List<Message> listSentMessages;
	
	public Room(Account firstInterlocutor,Account secondInterlocutor)
	{
		interlocutors = new ArrayList<Account>();
		listSentMessages = new ArrayList<Message>();
		
		interlocutors.add(firstInterlocutor);
		interlocutors.add(secondInterlocutor);
	}
	
	public List<Account> getInterlocutors()
	{
		return interlocutors;
	}
	
	public void addInterlocutor (Account user)
	{
		interlocutors.add(user);
	}
	
	public void deleteInterlocutor (Account user)
	{
		interlocutors.remove(user);
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
			return listSentMessages.get(0).getSender().getName();
		else
			return "";
	}
	
	void addMessage(Message newMessage)
	{
		listSentMessages.add(0,newMessage);
	}
		
}
