package com.strategicgains.cli;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usage {
	private String name;
	private String description;
	private List<Usage> options;
	private String heading;
	private int indentLevel;

	private Usage(Builder b) {
		this.name = b.name;
        this.description = b.description;
        this.options = List.copyOf(b.options);
        this.heading = b.heading;
        this.indentLevel = b.indentLevel;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Usage: ").append(name).append(" : ").append(description).append("\n");

		if (!options.isEmpty()) {
			sb.append(heading).append(":\n");
			options.forEach(option -> option.toString(sb, indentLevel, ""));
		}

		return sb.toString();
	}

	private void toString(StringBuilder sb, int indent, String prefix) {
		String linePrefix = (prefix == null ? "" : prefix) + " ".repeat(indent);
		sb.append(linePrefix)
			.append(name)
			.append(" : ")
			.append(description)
			.append("\n");

		String childPrefix = linePrefix;// + " ".repeat(indent);
		options.forEach(option -> option.toString(sb, indent, childPrefix));
	}

	// Usage Builder
	public static Builder builder(String name) {
		return new Builder(name);
	}

	public static class Builder {
		private final String name;
		private Set<String> aliases = new HashSet<>();
		private String description = "";
		private List<Usage> options = new ArrayList<>();
		private String heading = "Commands";
		private int indentLevel = 2;

		public Builder(String name) {
			this.name = name;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder heading(String heading) {
			this.heading = heading;
			return this;
		}

		public Builder indent(int spaces) {
			this.indentLevel = spaces;
			return this;
		}

		public Builder option(Usage usage) {
			options.add(usage);
			return this;
		}

		public Builder option(String name, String description) {
			options.add(new Usage.Builder(name).description(description).build());
			return this;
		}

		public Usage build() {
			return new Usage(this);
		}

		public Builder alias(String alias) {
			aliases.add(alias);
			return this;
		}
	}
}
