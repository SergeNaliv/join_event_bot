package com.gitHub.SergeNaliv.join_event_bot.command;


import org.telegram.telegrambots.meta.api.objects.Update;

import com.gitHub.SergeNaliv.join_event_bot.service.BotSendMessageService;



/**
* START {@link Command}.
*/

public class StartCommand implements Command {

private final BotSendMessageService botSendMessageService;
	
	

	public final static String START_MESSAGE = "I know such commands: \n"
												+ "/+      to  join event\n"
												+ "/+X     to  take someone with you\n"
												+ "/-      to  left event\n"
												+ "/-X     to  cancel guest invitation\n"
												+ "/price  to  X  set event price\n"
												+ "/list   to  look list of people\n"
												+ "/reset  to  start new event"	;
	
	public StartCommand(BotSendMessageService botSendMessageService) {
	       this.botSendMessageService = botSendMessageService;
	   }
	
	@Override
	public void execute(Update update) {
		// TODO Auto-generated method stub
		 botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
	}
	
}