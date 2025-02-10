package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public enum Commands {
	ENV(new Env(), "e"),
	HELP(new Help(), "h"),
	OAS(new OasImport(), "o"),
	RESOURCE(new Resource(), "r");

	private Command command;
	private String synonym;

	Commands(Command command, String synonym) {
		this.command = command;
		this.synonym = synonym;
	}

	public void execute(String... input) {
		String response = command.execute(input);

		if (response != null) {
			System.out.println(response);
		}
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
			if (c.command().equals(command) || c.synonym.equals(command)) {
				return c;
			}
		}

		throw new IllegalArgumentException("Unknown command: " + command);
	}
}
