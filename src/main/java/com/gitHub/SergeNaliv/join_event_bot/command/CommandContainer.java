package com.gitHub.SergeNaliv.join_event_bot.command;




import com.google.common.collect.ImmutableMap;
import static com.gitHub.SergeNaliv.join_event_bot.command.CommandName.*;

import com.gitHub.SergeNaliv.join_event_bot.service.BotSendMessageService;

/**
* Container of the {@link Command}s, which are using for handling telegram commands.
*/
public class CommandContainer {

   private  final ImmutableMap<String, Command> commandMap;
   private final Command unknownCommand;
 

   public CommandContainer(BotSendMessageService botSendMessageService) {

	   commandMap = ImmutableMap.<String, Command>builder()
			   .put(START.getCommandName(), new StartCommand(botSendMessageService))
			   .put(HELP.getCommandName(), new StartCommand(botSendMessageService))
			   .put(ADD.getCommandName(), new AddCommand(botSendMessageService))
			   .put(REMOVE.getCommandName(), new RemoveCommand(botSendMessageService))
			   .put(RESET.getCommandName(), new ResetCommand(botSendMessageService))
               .put(LIST.getCommandName(), new ListCommand(botSendMessageService))
               .put(PRICE.getCommandName(), new PriceCommand(botSendMessageService))
               .build();
       
       unknownCommand = new UnknownCommand(botSendMessageService);
   }

   public Command retrieveCommand(String commandIdentifier) {
       return commandMap.getOrDefault(commandIdentifier, unknownCommand);
   }

}