package com.dev_console;

public interface IConsoleCommand {

	String getCommandName();
	
	CommandResponse runCommand();
}

