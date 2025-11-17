package com.strategicgains.cli.command;

import java.io.Console;

import com.strategicgains.cli.CommandLine;
import com.strategicgains.cli.CommandLineParser;
import com.strategicgains.cli.Config;

public class CommandContext {
	private static final String CLI_PATTERN = "c~dhv";
//	private static final String CLI_PATTERN = "[-d|--debug][-h|--help][-v|--version][-c|--config FILE]";
	private static final CommandLineParser CLI_PARSER = new CommandLineParser(CLI_PATTERN);

	private CommandLine commandLine;
	private String commandName;
	private boolean verbose;
	private boolean debug;
	private boolean help;
	private boolean version;
	private String configFile;
	private Config config;
	private Console console;

	public CommandContext(String[] args) {
		CommandLine commandLine = CLI_PARSER.parse(args);
		this.commandLine = commandLine;

		if (commandLine.hasArguments()) {
			setCommandName(commandLine.getArgument(0));
		}

		setHelp(commandLine.isOptionSet('h'));
//		setVersion(commandLine.isOptionSet('v'));
		setVerbose(commandLine.isOptionSet('v'));
		setDebug(commandLine.isOptionSet('d'));

		if (commandLine.isOptionSet('c')) {
			setConfigFile(commandLine.getOptionArgument('c'));
		}
	}

	public String[] getArguments() {
		return commandLine.getArguments();
	}

	public String getCommandName() {
		return commandName;
	}

	private void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getConfigFile() {
		return configFile;
	}

	public boolean hasConfigFile() {
		return (this.configFile != null && !this.configFile.isEmpty());
	}

	private void setConfigFile(String fileLocation)
	{
		this.configFile = fileLocation;
	}

	public boolean isVersion() {
		return version;
	}

	private void setVersion(boolean isVersion) {
		this.version = isVersion;
	}

	public boolean isVerbose() {
		return verbose;
	}

	private void setVerbose(boolean isVerbose) {
		if (isVerbose) System.out.println("Verbose mode enabled");
		this.verbose = isVerbose;
	}

	public boolean isHelp() {
		return help;
	}

	private void setHelp(boolean isHelp) {
		this.help = isHelp;
	}

	public boolean isDebug() {
		return debug;
	}

	private void setDebug(boolean isDebug) {
		if (isDebug) System.out.println("Debug mode enabled");
		this.debug = isDebug;
	}

	public Config getConfig() {
		return config;
	}
	
	public void setConfig(Config config) {
		this.config = config;
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}
}
