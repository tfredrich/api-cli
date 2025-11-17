
package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public class Env
extends AbstractCommand {
	private static final String COMMAND_NAME = "environment";
	private static final String COMMAND_DESCRIPTION = "Manage promotion environments.";
	private static final String ALIAS = "env";
	public Env() {
		super(COMMAND_NAME, COMMAND_DESCRIPTION);
		addAlias(ALIAS);
	}

	@Override
	public int execute(CommandContext context) {
		String[] args = context.getArguments();

		if (args.length < 2) {
			throw new IllegalArgumentException("missing environment command");
		}

		switch (args[1]) {
		case "add":
			return add(args);
		case "commit":
			return commit(args);
		case "diff":
			return diff(args);
		case "list", "ls":
			return list(args);
		case "pull":
			return pull(args);
		case "push":
			return push(args);
		case "remove", "rm":
			return remove(args);
		case "status":
			return status();
		case "use":
			return use(args);
		default:
		}

		return 1;
	}

	private int add(String[] args) {
		if (args.length < 4) {
			throw new IllegalArgumentException("missing environment name or URL");
		}

		return add(args[2], args[3]);
	}

	private int add(String name, String baseUrl) {
		// TODO Auto-generated method stub
		return 1;
	}

	private int commit(String[] args) {
		// TODO Auto-generated method stub
		return 1;
	}

	private int diff(String[] args) {
		// TODO Auto-generated method stub
		return 1;
	}


	private int list(String[] args) {
		if (args.length < 2) {
			return listAll();
		}

		return listEnvironment(args[2]);
	}

	private int listAll() {
		// TODO Auto-generated method stub
		return 1;
	}

	private int listEnvironment(String environment) {
		if (environment != null) {
			return listEnvironment(environment);
		}

		return 1;
	}

	private int pull(String[] args) {
		// TODO Auto-generated method stub
		return 1;
	}

	private int push(String[] args) {
		// TODO Auto-generated method stub
		return 1;
	}

	private int remove(String[] args) {
		if (args.length < 3) {
			throw new IllegalArgumentException("missing environment name");
		}
		// TODO Auto-generated method stub
		return 1;
	}

	private int status() {
		// TODO Auto-generated method stub
		return 1;
	}

	private int use(String[] args) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Usage getUsage() {
		Usage.Builder b = Usage.builder(getName() + " <operation> [parameters]")
			.description(getDescription())
			.heading("Environment operations")
			.option("add <name> <baseUrl>", "Add a new environment.")
			.option("commit [name]", "Commit the changes in the environment to the remote.")
			.option("diff <name>", "Show the differences between the current environment and the specified environment.")
			.option("list", "List all environments.")
			.option("pull [name]", "Pull the environment configuration (or all environments).")
			.option("push <name>", "Promote the environment configuration to the named environment.")
			.option("remove <name>", "Remove an environment.")
			.option("status", "Show the current environment and status information.")
			.option("use <name>", "Switch to a different environment.");

		if (hasAliases()) getAliases().forEach(b::alias);
		return b.build();
	}
}
