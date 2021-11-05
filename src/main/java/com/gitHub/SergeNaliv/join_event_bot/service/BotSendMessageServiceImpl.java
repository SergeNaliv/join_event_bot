package com.gitHub.SergeNaliv.join_event_bot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.gitHub.SergeNaliv.join_event_bot.bot.JoinEventBot;




/**
* Implementation of {@link BotSendMessageService} interface.
*/

@Service
public class BotSendMessageServiceImpl implements BotSendMessageService  {

	   private final JoinEventBot joinEventBot;
	   
	   @Autowired
	   public BotSendMessageServiceImpl(JoinEventBot joinEventBot){
		   this.joinEventBot = joinEventBot;
	   }
	

	   @Override
	   public void sendMessage(String chatId, String message) {
	       SendMessage sendMessage = new SendMessage();
	       sendMessage.setChatId(chatId);
	       sendMessage.enableHtml(true);
	       sendMessage.setText(message);

	       try {
	           joinEventBot.execute(sendMessage);
	       } catch (TelegramApiException e) {
	           //todo add logging to the project.
	           e.printStackTrace();
	       }
	   }
	}

