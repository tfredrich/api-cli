package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public class Oas extends AbstractCommand {
	private static final String NAME = "openapi";
	private static final String DESCRIPTION = "Manage OpenAPI Specification files for automatic URL configuration.";
	private static final String ALIAS = "oas";

	protected Oas() {
		super(NAME, DESCRIPTION);
		addAlias(ALIAS);
	}

	@Override
	public String execute(String[] args) {
		if (args.length == 0) {
			return getUsage().toString();
		}

		switch (args[0]) {
		case "add":
			return add(args);
		case "list", "ls":
			return list();
		case "pull":
			return pull(args);
		case "remove", "rm":
			return remove(args);
		default:
			return getUsage().toString();
		}
	}

	private String add(String[] args) {
		if (args.length < 4) {
			throw new IllegalArgumentException("missing name or file|url");
		}
		return null;
	}

	private String list() {
		// TODO Auto-generated method stub
		return null;
	}

	private String pull(String[] args) {
		if (args.length == 1) {	// pull all
		// TODO Auto-generated method stub
		return null;
		}

		// pull one
       	return null;
	}

	private String remove(String[] args) {
		if (args.length < 2) {
            throw new IllegalArgumentException("missing name");
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usage getUsage() {
		Usage.Builder b = Usage.builder(getName() + " <operation> [parameters]")
			.description(getDescription())
			.heading("Open API Management Commands")
			.option("add <name> <file|url>", "Add a new OAS file.")
			.option("list", "List all OAS files.")
			.option("pull [name]", "Refresh the OAS file.")
			.option("remove <name>", "Remove an OAS file.");

		if (hasAliases()) getAliases().forEach(b::alias);

		return b.build();
	}
}
