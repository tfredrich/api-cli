package com.strategicgains.cli.command;

import java.util.List;

import com.strategicgains.cli.Usage;

public abstract class AbstractCommand
implements Command
{
	private String name;
	private List<String> aliases;
	private String description;

	protected AbstractCommand(String name, String description) {
		this.name = name;
		this.description = description;
	}

	protected void addAlias(String alias) {
		if (aliases == null) {
			aliases = new java.util.ArrayList<>();
		}
		aliases.add(alias);
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public boolean hasAliases() {
		return (aliases != null && !aliases.isEmpty());
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
