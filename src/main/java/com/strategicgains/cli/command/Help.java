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
	public String execute(String[] args) {
//		return getUsage(args).toString();
		return "Hmm...";
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
