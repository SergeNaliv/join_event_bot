package com.gitHub.SergeNaliv.join_event_bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.gitHub.SergeNaliv.join_event_bot.event.EventService;
import com.gitHub.SergeNaliv.join_event_bot.event.Person;
import com.gitHub.SergeNaliv.join_event_bot.service.BotSendMessageService;



/**
* Remove {@link Command}.
*/

public class RemoveCommand implements Command{
	
	private final BotSendMessageService botSendMessageService;
	private EventService eventService;
	private Person person;
		

			
		public RemoveCommand(BotSendMessageService botSendMessageService) {
		       this.botSendMessageService = botSendMessageService;
		       
		   }
		
		@Override
		public void execute(Update update) {
			// TODO Auto-generated method stub
			person = new Person(update);
			eventService=new EventService(update.getMessage().getChatId().toString());
			String message = update.getMessage().getText();
			
			if (message.equals("/-")){	
			 botSendMessageService.sendMessage(update.getMessage().getChatId().toString(),
					eventService.leaveEvent(person));
			} else botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), 
					 eventService.cancelGuests(person, message.substring(2)));
		}

}
