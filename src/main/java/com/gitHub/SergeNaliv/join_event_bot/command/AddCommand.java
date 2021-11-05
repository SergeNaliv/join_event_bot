package com.gitHub.SergeNaliv.join_event_bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.gitHub.SergeNaliv.join_event_bot.event.EventService;
import com.gitHub.SergeNaliv.join_event_bot.event.Person;
import com.gitHub.SergeNaliv.join_event_bot.service.BotSendMessageService;



/**
* Add {@link Command}.
*/

public class AddCommand implements Command{

	private final BotSendMessageService botSendMessageService;
	private EventService eventService;
	private Person person;
	
	public AddCommand(BotSendMessageService botSendMessageService) {
	       this.botSendMessageService = botSendMessageService;
	       }
	
	@Override
	public void execute(Update update) {
		// TODO Auto-generated method stub
				person = new Person(update);
				eventService=new EventService(update.getMessage().getChatId().toString());
		String message = update.getMessage().getText();
		
		if (message.equals("/+")){		
		botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), 
				 eventService.joinEvent(person));
		} else botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), 
				 eventService.callGuests(person, message.substring(2)));
		
	}
	
}
