package com.strategicgains.cli.command;

import java.util.List;

import com.strategicgains.cli.Usage;

/**
 * Interface for a command that can be executed by the shell.
 */
public interface Command {
	/**
	 * Execute the command with the given arguments.
	 * 
	 * @param context the command context.
	 * @return an integer exit code (0 for success, non-zero for failure).
	 */
	int execute(CommandContext context);

	/**
	 * Get the list of aliases for the command.
	 * 
	 * @return a list of aliases.
	 */
	List<String> getAliases();

	/**
	 * Determine if the command has any aliases.
	 * 
	 * @return true if the command has aliases, false otherwise.
	 */
	boolean hasAliases();

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
