package com.strategicgains.cli;

import com.strategicgains.cli.command.Command;
import com.strategicgains.cli.command.CommandRegistry;

/**
 * API Command Line Interface (apicli).
 * 
 * The main entry point for apicli, the Command-line API Shell.
 */
public class Main {
	public static void main(String[] args) throws Exception {
//		Config config = Config.load();

		// If there are no arguments, enter shell mode.
		if (args.length == 0) {
			Shell.run();
			System.exit(0);
		}

		// Otherwise, process the arguments.
		try {
			Command c = CommandRegistry.find(args[0]);
			c.execute(args);
			System.exit(0);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
