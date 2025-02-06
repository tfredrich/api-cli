package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public class OasImport extends AbstractCommand {
	private static final String NAME = "oas";
	private static final String DESCRIPTION = "Manage OpenAPI Specification files for automatic URL configuration.";

	protected OasImport() {
		super(NAME, DESCRIPTION);
	}

	@Override
	public String execute(String[] args) {
		// TODO Auto-generated method stub
		return "execute oas import";
	}

	@Override
	public Usage getUsage() {
		return super.getUsage()
			.withOption("add <name> <file|url>", "Add a new OAS file.")
			.withOption("list", "List all OAS files.")
			.withOption("pull [name]", "Refresh the OAS file.")
			.withOption("remove <name>", "Remove an OAS file.");
	}
}
