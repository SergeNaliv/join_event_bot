package com.gitHub.SergeNaliv.join_event_bot.command;

public enum CommandName {

	START ("/start"), 
	HELP ("/help"),
	RESET ("/reset"),
	LIST ("/list"),
	ADD ("/+"),	
	REMOVE("/-"),
	PRICE("/price");
	
	 private final String commandName;

	   CommandName(String commandName) {
	       this.commandName = commandName;
	   }

	   public String getCommandName() {
	       return commandName;
	   }

}
