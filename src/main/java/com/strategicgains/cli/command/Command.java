package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

/**
 * Interface for a command that can be executed by the shell.
 */
public interface Command {
	/**
	 * Execute the command with the given arguments.
	 * 
	 * @param args
	 * @return
	 */
	String execute(String[] args);

	/**
	 * Get the name of the command that is used in the shell or in scripts.
	 * 
	 * @return the name of the command.
	 */
	String getName();

	/**
	 * Get the description of the command.
	 * 
	 * @return a string describing what the command does.
	 */
	String getDescription();

	/**
	 * Get help for usage of the command.
	 * 
	 * @return a string describing the usage of the command.
	 */
	Usage getUsage();
}
