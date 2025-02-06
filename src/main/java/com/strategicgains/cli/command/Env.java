package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public class Env extends AbstractCommand {
	public Env() {
		super("env", "Manage promotion environments.");
	}

	@Override
	public String execute(String[] args) {
		return "execute env";
	}

	@Override
	public Usage getUsage() {
		return super.getUsage()
			.withOption("add <name> <url>", "Add a new environment.")
			.withOption("commit [name]", "Commit the changes in the environment to the remote.")
			.withOption("diff <name>", "Show the differences between the current environment and the specified environment.")
			.withOption("list", "List all environments.")
			.withOption("pull [name]", "Pull the environment configuration (or all environments).")
			.withOption("push <name>", "Promote the environment configuration to the named environment.")
			.withOption("remove <name>", "Remove an environment.")
			.withOption("status", "Show the current environment and status information.")
			.withOption("use <name>", "Switch to a different environment.");
	}
}
