package com.strategicgains.cli;

import java.io.Console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strategicgains.cli.command.Command;
import com.strategicgains.cli.command.CommandRegistry;

public class Shell {
	private static final Logger LOG = LoggerFactory.getLogger(Shell.class);
	private static final String PROMPT = "> ";
	private static final String EXIT = "exit";

	private Config config;

	private Shell(Config config) {
		this.config = config;
	}

	public static int run(Config config) {
		Shell shell = new Shell(config);
		return shell.execute();
	}

	private int execute() {
		Console console = System.console();

		if (console == null) {
			LOG.error("No console available; exiting interactive mode.");
			return 1;
		}

		console.printf("Entering interactive mode%n");
		console.printf("Type 'exit' to exit%n");

		while (true) {
			console.printf(PROMPT);
			if (!executeCommand(console)) {
				break;
			}
		}

		console.printf("Exiting interactive mode%n");
		return 0;
	}

	private boolean executeCommand(Console console) {
		String input = console.readLine();
		String[] args = input.split("\\s+");

		if (args[0].equals(EXIT)) {
			return false;
		}

		try {
			Command c = CommandRegistry.find(args[0]);
            c.execute(args);
        }
		catch (IllegalArgumentException e) {
			console.printf("Error: %s%n", e.getMessage());
        }

		return true;
	}
}
