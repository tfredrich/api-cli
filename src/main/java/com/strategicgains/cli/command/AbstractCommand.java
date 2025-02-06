package com.strategicgains.cli.command;

import com.strategicgains.cli.Usage;

public abstract class AbstractCommand implements Command {
	private String name;
	private String description;

	protected AbstractCommand(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Usage getUsage() {
		return new Usage(getName(), getDescription());
	}
}
