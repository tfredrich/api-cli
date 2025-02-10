package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public class Env extends AbstractCommand {
	public Env() {
		super("env", "Manage promotion environments.");
	}

	@Override
	public String execute(String[] args) {
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

		return null;
	}

	private String add(String[] args) {
		if (args.length < 4) {
			throw new IllegalArgumentException("missing environment name or URL");
		}

		return add(args[2], args[3]);
	}

	private String add(String name, String baseUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	private String commit(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	private String diff(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}


	private String list(String[] args) {
		if (args.length < 2) {
			return listAll();
		}

		return listEnvironment(args[2]);
	}

	private String listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private String listEnvironment(String environment) {
		if (environment != null) {
			listEnvironment(environment);
		}

		return null;
	}

	private String pull(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	private String push(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	private String remove(String[] args) {
		if (args.length < 3) {
			throw new IllegalArgumentException("missing environment name");
		}
		// TODO Auto-generated method stub
		return null;
	}

	private String status() {
		// TODO Auto-generated method stub
		return null;
	}

	private String use(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usage getUsage() {
		return super.getUsage()
			.withOption("add <name> <baseUrl>", "Add a new environment.")
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
