package com.strategicgains.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strategicgains.cli.command.Command;
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
//	private static final String CLI_PATTERN = "[-d|--debug][-h|--help][-v|--version][-c|--config FILE]";
	private static final String CLI_PATTERN = "c~dhv";

	public static void main(String[] args) {
		CommandLine cl = new CommandLineParser(CLI_PATTERN).parse(args);
		if (cl.isOptionSet('h')) {
			System.out.println(CommandRegistry.printHelp());
			System.exit(0);
		}

		if (cl.isOptionSet('v')) {
			System.out.println(String.format("%s - %s version %s", APP_NAME, APP_DESCRIPTION, APP_VERSION));
			System.exit(0);
		}

		boolean isDebug = false;

		if (cl.isOptionSet('d')) {
			isDebug = true;
			System.out.println("Debug mode enabled");
		}

		Config config = null;

		try
		{
			config = Config.load(cl.getOptionArgument('c'), isDebug);
			if (isDebug) config.setDebug(true);
		}
		catch (Exception e)
		{
			LOG.error("Error loading configuration: {}", e.getMessage());
			System.exit(1);
		}

		// If there are no arguments, enter shell mode.
		if (args.length == 0) {
			Shell.run(config);
			System.exit(0);
		}

		// Otherwise, process the arguments.
		try {
			Command c = CommandRegistry.find(args[0]);
			c.execute(args);
			System.exit(0);
		} catch (UnknownCommandException e) {
			LOG.error(e.getMessage());
			System.exit(1);
		}
	}
}
