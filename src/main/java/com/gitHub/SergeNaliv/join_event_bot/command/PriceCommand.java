package com.gitHub.SergeNaliv.join_event_bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.gitHub.SergeNaliv.join_event_bot.event.EventService;
import com.gitHub.SergeNaliv.join_event_bot.service.BotSendMessageService;



/**
* Price {@link Command}.
*/

public class PriceCommand implements Command{

	private final BotSendMessageService botSendMessageService;
	private EventService eventService;
	
	
	public PriceCommand(BotSendMessageService botSendMessageService) {
	       this.botSendMessageService = botSendMessageService;
	       }
	
	@Override
	public void execute(Update update) {
		// TODO Auto-generated method stub
		eventService=new EventService(update.getMessage().getChatId().toString());
		
		botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), 
				 eventService.setEventPrice(update.getMessage().getText().split("/price")[1]));
	}
	

}
