package com.gitHub.SergeNaliv.join_event_bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.gitHub.SergeNaliv.join_event_bot.service.BotSendMessageService;

/**
* Unknown {@link Command}.
*/
public class UnknownCommand implements Command {

   public static final String UNKNOWN_MESSAGE = "Unknown command!.\n Type /help or /start , to look commands list";

   private final BotSendMessageService botSendMessageService;

   public UnknownCommand(BotSendMessageService botSendMessageService) {
       this.botSendMessageService = botSendMessageService;
   }

   @Override
   public void execute(Update update) {
       botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
   }
}