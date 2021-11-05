package com.gitHub.SergeNaliv.join_event_bot.event;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

public class EventFileEntity implements EventEntity {

	private String eventRecord;
	private Event event;
	private Person person;
	
	public EventFileEntity(String eventId) {
		event=new Event();
		this.event.setEventId(eventId);
		this.eventRecord = ("/"+ eventId+ ".txt");
		File record = new File(eventRecord);
		if (!record.exists()) {
			try (FileWriter file = new FileWriter(eventRecord)) {
				event.setEventPrice("0");
	            file.write(event.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	@Override
	public Event getEvent() {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader(eventRecord)) {
			
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			
			String price = (String)jsonObject.get("price");
			event.setEventPrice(price);
			
			HashMap<String, String> personList= (HashMap<String, String>)jsonObject.get("eventList");
			event.setEventList(personList);
			
			HashMap<String, String> guests = (HashMap<String, String>)jsonObject.get("guestList");
			event.setGuestList(guests);
			
			
		}  catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return event;
	}

	
	
	@Override
	public void updateEvent(Event event) {
		// TODO Auto-generated method stub
		
		 try (FileWriter file = new FileWriter(eventRecord)) {
	            file.write(event.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}

	@Override
	public void createEntity() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void deleteEntity() {
		// TODO Auto-generated method stub
		
	}

	
	
}
