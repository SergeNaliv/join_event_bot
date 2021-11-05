package com.gitHub.SergeNaliv.join_event_bot.event;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Person {
	
	private String personId;
	private String personName;
	
	
	public Person(Update update) {
	 	this.personId=update.getMessage().getFrom().getId().toString();
	 	this.personName=update.getMessage().getFrom().getFirstName();
	}
	
	public Person(String id, String Name) {
		this.personId=id;
		this.personName=Name;
	}
	
	public Person(String person) {
		String temp[] = person.split(":");
		this.personId=temp[0];
		this.personName=temp[1];
	}
	
	
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	@Override
	public String toString(){
		return (personId + ":" + personName+"\n");
	}
	
	

}