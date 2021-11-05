package com.gitHub.SergeNaliv.join_event_bot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.gitHub.SergeNaliv.join_event_bot.command.CommandContainer;
import com.gitHub.SergeNaliv.join_event_bot.service.BotSendMessageServiceImpl;

@Component
public class JoinEventBot extends TelegramLongPollingBot {
	
	public static String COMMAND_PREFIX = "/";
	
	
	@Value ("${bot.username}")
	private String username;
	@Value ("${bot.token}")
	private String token;
	
	private final CommandContainer commandContainer;
	
	
	
	public JoinEventBot() {
		this.commandContainer = new CommandContainer(new BotSendMessageServiceImpl(this));
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		 if (update.hasMessage() && update.getMessage().hasText()) {
	           String message = update.getMessage().getText().trim();
	           
	         
	           
	          if (message.startsWith(COMMAND_PREFIX)) {
	               String commandIdentifier = message.split(" ")[0].toLowerCase();
	               
	          if (commandIdentifier.startsWith("/+") || commandIdentifier.startsWith("/-")) {
	        	  commandIdentifier=commandIdentifier.substring(0, 2) ;    
	          }
	               
	               commandContainer.retrieveCommand(commandIdentifier).execute(update);
	           } 
	              
	    }
	           
	       
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return token;
	}

}
