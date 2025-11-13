package com.strategicgains.cli.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.strategicgains.cli.exception.UnknownCommandException;

public class CommandRegistry
{
	private static final Map<String, Command> COMMANDS = new HashMap<>();
	static
	{
		register(new Env());
		register(new Help());
		register(new Oas());
		register(new Resource());
	}

	private CommandRegistry()
	{
		// Prevent instantiation.
	}

	private static void register(Command command)
	{
		COMMANDS.put(command.getName(), command);

		if (command.hasAliases())
		{
			command.getAliases().forEach(alias -> COMMANDS.put(alias, command));
		}
	}

	public static Command find(String name)
	{
		Command c = COMMANDS.get(name);

		if (c == null)
		{
			throw new UnknownCommandException(name);
		}

		return c;
	}

	public static List<Command> values()
	{
		return COMMANDS.values().stream().distinct().toList();
	}

	public static void printHelp() {
		COMMANDS.get(Help.COMMAND_NAME).execute(new String[] {Help.COMMAND_NAME});
		// TODO Auto-generated method stub
		
	}
}
