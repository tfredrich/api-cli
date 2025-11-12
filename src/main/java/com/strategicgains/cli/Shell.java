package com.strategicgains.cli;

import com.strategicgains.cli.command.Command;
import com.strategicgains.cli.command.CommandRegistry;
import com.strategicgains.cli.util.Utils;

public class Shell {
	private static final String EXIT = "exit";

	private Shell() {
		// Prevent instantiation.
	}

	public static void run() {
		Shell shell = new Shell();
		shell.execute();
	}

	private void execute() {
		System.out.println("Entering interactive mode");
		System.out.println("Type 'exit' to exit");
		while (true) {
			System.out.print("> ");
			if (!executeCommand()) {
				break;
			}
		}

		System.out.println("Exiting interactive mode");
	}

	private boolean executeCommand() {
		String input = Utils.readInput();
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
