package com.strategicgains.cli;

import java.util.ArrayList;
import java.util.List;

public class Usage {
	private String name;
	private String description;
	private List<Usage> options = new ArrayList<>();

	public Usage(String name, String description) {
		this.name = name;
        this.description = description;
	}

	public Usage withOption(String name, String description) {
		return withOption(new Usage(name, description));
	}

	public Usage withOption(Usage option) {
		options.add(option);
		return this;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Usage> getOptions() {
		return options;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Usage: ").append(name).append(" : ").append(description).append("\n");

		if (!options.isEmpty()) {
			sb.append("Commands:\n");
			toString(sb, options, 0);
		}

		return sb.toString();
	}

	private void toString(StringBuilder sb, List<Usage> options, int indent) {
		if (indent > 0) {
			String prefix = "  ".repeat(indent);
			sb.append(prefix).append(getName()).append(" : ").append(getDescription()).append("\n");
		}

		for (Usage option : options) {
            option.toString(sb, option.getOptions(), indent + 1);
        }
	}
}
