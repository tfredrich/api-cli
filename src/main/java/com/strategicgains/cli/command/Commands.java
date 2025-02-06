package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public enum Commands {
	ENV(new Env()),
	HELP(new Help()),
	OAS(new OasImport()),
	RESOURCE(new Resource());

	private Command command;

	Commands(Command command) {
		this.command = command;
	}

	public void execute(String... input) {
		System.out.println(command.execute(input));
	}

	public String command() {
		return command.getName();
	}

	public Usage usage() {
		return command.getUsage();
	}

	public String description() {
		return command.getDescription();
	}

	public static Commands fromString(String command) {
		for (Commands c : Commands.values()) {
			if (c.name().equalsIgnoreCase(command)) {
				return c;
			}
		}
		throw new IllegalArgumentException("Unknown command: " + command);
	}
}
