package com.gitHub.SergeNaliv.join_event_bot.event;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Event {

	private String eventId;
	private HashMap <String, String> eventList;
	private HashMap <String, String> guestList;
	private String eventPrice;
	
	public Event() {
		this.eventList= new HashMap<String, String>();
		this.guestList= new HashMap<String, String>();
	}
	
	public String getEventId() {
		return eventId;
	}
	
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	

	public HashMap<String, String> getEventList() {
		return eventList;
	}
	

	public void setEventList(HashMap<String, String> eventList) {
		this.eventList = eventList;
	}
	

	public String getEventPrice() {
		return eventPrice;
	}
	

	public void setEventPrice(String eventPrice) {
		this.eventPrice = eventPrice;
	}
	
	
	public void clearEventList() {
		eventList.clear();
	}
	
	public void addPerson(Person person) {
		eventList.put(person.getPersonId(), person.getPersonName());
	}
	
	public void removePerson(Person person) {
		eventList.remove(person.getPersonId());
	}
	
		
	public HashMap<String, String> getGuestList() {
		return guestList;
	}

	public void setGuestList(HashMap<String, String> guestList) {
		this.guestList = guestList;
	}

	public void clearGuestList() {
		guestList.clear();
	}

	public void updateGuests (Person person, int count) {
		
		guestList.put(person.toString(), Integer.toString(count));
		
		}
		
		
	public void cancelGuests (Person person) {
							
		guestList.remove(person.toString());
		
		 }
		 
		
		
	@Override
	public String toString() {
		
		JSONObject eventJSON = new JSONObject();
		eventJSON.put("price", eventPrice);
		eventJSON.put("eventList", eventList);
		eventJSON.put("guestList", guestList);
		return eventJSON.toJSONString();
			
	}


}
