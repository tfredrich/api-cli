package com.strategicgains.cli.command;

import java.util.stream.Stream;

import com.strategicgains.cli.Usage;

public class Help extends AbstractCommand {
	protected Help() {
		super("help", "[command] : Get help for apicli or a particular command.");
	}

	@Override
	public String execute(String[] args) {
		return getUsage(args).toString();
	}

	@Override
	public Usage getUsage() {
        Usage usage = new Usage("apicli [command [parameters]", "No parameters for shell access.");
		Stream.of(Commands.values()).filter(command -> !Commands.HELP.equals(command))
				.forEach(command -> usage.withOption(command.usage()));
		return usage;
	}

	private Usage getUsage(String[] args) {
		if (args.length == 1) {
			return getUsage();
		}

		try {
			Commands command = Commands.fromString(args[1]);
			return command.usage();
		} catch (IllegalArgumentException e) {
			return new Usage(args[1], e.getMessage());
		}
	}
}
