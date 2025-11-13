package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public class Help
extends AbstractCommand {
	public static final String COMMAND_NAME = "help";
	private static final String ALIAS = "h";
	private static final String DESCRIPTION = "[command] : Get help for apicli or a particular command.";
	protected Help() {
		super(COMMAND_NAME, DESCRIPTION);
		addAlias(ALIAS);
	}

	@Override
	public String execute(String[] args) {
		return getUsage(args).toString();
	}

	@Override
	public Usage getUsage() {
        Usage usage = new Usage("apicli [command [parameters]", "No parameters for shell access.");
		CommandRegistry.values().stream().filter(command -> !command.getName().equals(this.getName()))
				.forEach(command -> usage.withOption(command.getUsage()));
		return usage;
	}

	private Usage getUsage(String[] args) {
		if (args.length == 1) {
			return getUsage();
		}

		try {
			Command command = CommandRegistry.find(args[1]);
			return command.getUsage();
		} catch (IllegalArgumentException e) {
			return new Usage(args[1], e.getMessage());
		}
	}
}
