package com.strategicgains.cli.command;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.strategicgains.cli.Usage;
import com.strategicgains.cli.exception.UnknownCommandException;

public class CommandRegistry
{
	private static final Map<String, Command> COMMANDS = new LinkedHashMap<>();
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

	public static String printHelp() {
		return getAllUsage().toString();
	}

	private static Usage getAllUsage() {
		Usage.Builder b = Usage.builder("apicli [command [parameters]")
			.description("No command or parameters for shell access.")
			.heading("Commands");
		CommandRegistry.values().stream().forEach(command -> b.option(command.getUsage()));
		return b.build();
	}

	public static Usage getUsage(String[] args) {
		if (args.length <= 1) {
			return getAllUsage();
		}

		Command command = CommandRegistry.find(args[1]);
		return command.getUsage();
	}
}
