package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public class Resource extends AbstractCommand {
	public Resource() {
		super("resource", "Manage resource types and their URLs.");
	}

	@Override
	public String execute(String[] args) {
		return "executing resource command";
	}

	@Override
	public Usage getUsage() {
		return super.getUsage()
			.withOption("add <name> <http_method> <url>", "Add a resource type with a URL.")
			.withOption("remove <name>", "Remove a resource type.")
			.withOption("list [name]", "List resource type(s) and their URLs.");
	}
}
