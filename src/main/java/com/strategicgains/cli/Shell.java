package com.strategicgains.cli;

import java.io.Console;

import com.strategicgains.cli.command.Command;
import com.strategicgains.cli.command.CommandRegistry;
import com.strategicgains.cli.util.Utils;

public class Shell {
	private static final Logger LOG = Logger.getLogger(Shell.class);
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
		System.out.println("Entering interactive mode");
		System.out.println("Type 'exit' to exit");
		Console console = System.console();
		if (console == null) {
			LOG.error("No console available; exiting interactive mode.");
			return 1;
		}
		while (true) {
			console.printf(PROMPT);
			if (!executeCommand(console)) {
				break;
			}
		}

		LOG.info("Exiting interactive mode");
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
            System.err.println(e.getMessage());
        }

		return true;
	}
}
