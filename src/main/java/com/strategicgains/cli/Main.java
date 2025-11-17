package com.strategicgains.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strategicgains.cli.command.Command;
import com.strategicgains.cli.command.CommandContext;
import com.strategicgains.cli.command.CommandRegistry;
import com.strategicgains.cli.exception.UnknownCommandException;

/**
 * API Command Line Interface (apicli).
 * 
 * The main entry point for apicli, the Command-line API Shell.
 */
public class Main {
	private static final String APP_NAME = "apicli";
	private static final String APP_VERSION = "1.0.0";
	private static final String APP_DESCRIPTION = "Command-line API Shell";
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		CommandContext context = new CommandContext(args);

		if (context.isHelp()) {
			System.out.println(CommandRegistry.printHelp());
			System.exit(0);
		}

		if (context.isVersion()) {
			System.out.println(String.format("%s - %s version %s", APP_NAME, APP_DESCRIPTION, APP_VERSION));
			System.exit(0);
		}

		Config config = null;

		try
		{
			config = Config.load(context);
			context.setConfig(config);
		}
		catch (Exception e)
		{
			LOG.error("Error loading configuration: {}", e.getMessage());

			if (context.isDebug()) {
				e.printStackTrace();
			}

			System.exit(1);
		}

		// If there are no arguments, enter shell mode.
		if (args.length == 0) {
			int exitCode = Shell.run(context);
			System.exit(exitCode);
		}

		// Otherwise, process the arguments.
		try {
			Command c = CommandRegistry.find(context.getCommandName());
			int exitCode = c.execute(context);
			System.exit(exitCode);
		} catch (UnknownCommandException e) {
			LOG.error(e.getMessage());
			System.exit(1);
		}
	}
}
