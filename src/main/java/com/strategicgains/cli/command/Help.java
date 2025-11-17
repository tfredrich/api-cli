package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public class Help
extends AbstractCommand {
	public static final String COMMAND_NAME = "help";
	private static final String ALIAS = "h";
	private static final String DESCRIPTION = "Get help for apicli or a particular command.";
	protected Help() {
		super(COMMAND_NAME, DESCRIPTION);
		addAlias(ALIAS);
	}

	@Override
	public int execute(CommandContext context) {
		String[] args = context.getArguments();

		String helpText;

		if (args.length == 1) {
			helpText = CommandRegistry.printHelp();
		} else {
			Command command = CommandRegistry.find(args[1]);
			if (command == this) {
				Usage.Builder b = Usage.builder(getName() + " [command]")
					.description(getDescription())
					.heading("Help commands:");
				CommandRegistry.values().stream()
					.filter(c -> !getName().equals(c.getName()))
					.forEach(c -> b.option(c.getName(), c.getDescription()));
				helpText = b.build().toString();
			}
			else {
				helpText = command.getUsage().toString();
			}
		}

		System.out.println(helpText);
		return 0;
	}

	@Override
	public Usage getUsage() {
		Usage.Builder b = Usage.builder(getName() + " [command]")
			.description(getDescription())
			.option("command", "The command to get help for (optional).");

		if (hasAliases()) getAliases().forEach(b::alias);
		return b.build();
	}
}
