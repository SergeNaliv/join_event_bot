package com.gitHub.SergeNaliv.join_event_bot.event;
import java.util.Map;



public class EventService {

	
	private EventFileEntity eventFileEntity;
	private Event event;
	
	public EventService(String eventId) {
		this.eventFileEntity = new EventFileEntity(eventId);
		event = eventFileEntity.getEvent();
	}
	
	
	// answer to Reset {@link Command}
	
	public String resetEvent() {
		event.clearEventList();
		event.clearGuestList();
		eventFileEntity.updateEvent(event);
		return "New meeting. To join press /+";
	}
	
	
	// form answer to  List {@link Command}.
	
	public String showEventInfo() {
		String eventInfo;
		eventInfo=("price: " + event.getEventPrice()+" \n This event will join: \n");
		int personCount=0;
		
		// all users from Chat 
		
		for (Map.Entry<String, String> personList : event.getEventList().entrySet()) {
			eventInfo = eventInfo.concat(++personCount + ") "+ personList.getValue()+ "\n");
		}
		
		// guests
		
		eventInfo = eventInfo.concat("\n Our guestList: \n");
		for (Map.Entry<String, String> guestList : event.getGuestList().entrySet()) {
			Person person = new Person(guestList.getKey()); 
			eventInfo = eventInfo.concat(++personCount+ ") " +  guestList.getValue() + " called by " + person.getPersonName() );
			personCount=personCount-1 + Integer.parseInt(guestList.getValue());
		}
		
			
		eventInfo  = eventInfo.concat("\n Total: "+ personCount + " persons\n");
		
		if (personCount!=0){
			float pricePerPerson = Float.parseFloat(event.getEventPrice())/personCount;
						
			eventInfo = eventInfo.concat(String.format("\n Price per person = %.2f", pricePerPerson));
			
		} else {
			eventInfo = eventInfo.concat("\n Price per person "+ event.getEventPrice());
		}
		
		return eventInfo;
	}
	
	//form answer to  Add {@Link Command}
	
	public String joinEvent(Person person) {
		
		if  (event.getEventList().containsKey(person.getPersonId())) 
		{
			return "you're already in list";
		} else 
			event.getEventList().put(person.getPersonId(),person.getPersonName());
			eventFileEntity.updateEvent(event);
			return (person.getPersonName()+" will join us!");
	}
	
	
	
	public String callGuests(Person person, String guest) {
		
		guest=guest.trim();
		try { Integer.parseInt(guest);}
		catch (NumberFormatException e) {
			return (person.getPersonName()+" please, enter correct number of guests");
		}
		
		if (event.getGuestList().containsKey(person.toString())) {
		int guestCount = Integer.parseInt(	event.getGuestList().get(person.toString())) + Integer.parseInt(guest.trim()) ;
		event.updateGuests(person, guestCount);
				
		} else event.updateGuests(person, Integer.parseInt(guest) );
				
		eventFileEntity.updateEvent(event);
		
		return ("User " + person.getPersonName() + " invited " + guest + " more guests");
		
	}

	// form answer to Remove {@LinkCommand}

	public String leaveEvent(Person person) {
		if (!event.getEventList().containsKey(person.getPersonId())) {
		return (person.getPersonName() + " , you're not going to event");
		} else
		event.removePerson(person);
		eventFileEntity.updateEvent(event);		
		return ("user " + person.getPersonName() + " leaving event :(");
	}
	
	public String cancelGuests(Person person, String guest) {
		guest=guest.trim();
		try { Integer.parseInt(guest);}
		catch (NumberFormatException e) {
			return (person.getPersonName()+" please, enter correct number of guests");
		}
		
		if (event.getGuestList().containsKey(person.toString()) && 
			Integer.parseInt(event.getGuestList().get(person.toString())) >= Integer.parseInt(guest) ) {
				
			int guestCount = Integer.parseInt(event.getGuestList().get(person.toString())) - Integer.parseInt(guest);
			
				if (guestCount!=0) {
					event.updateGuests(person, guestCount);
					} else event.cancelGuests(person);
			
			eventFileEntity.updateEvent(event);
			
			return ("User " + person.getPersonName() + " cancelled " + guest + " more guests");
			
		} else
			
			return (person.getPersonName()+ ", you can't invite less then 0 people");
				
	}
	
	// form answer to Price {@Link Command}
	
	public String setEventPrice(String price) {
		
		try {
			price = price.trim();
			if (Integer.parseInt(price)<0) return ("price mustn't be lower than 0");
		} catch (NumberFormatException e) {
			return (" Error! Price must be a number!");
		}
		
		
		event.setEventPrice(price);
		eventFileEntity.updateEvent(event);
		return ("Current event price: " +price+" setted.");
	}
	
}
