package com.strategicgains.cli.command;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;

public abstract class AbstractHttpCommand extends AbstractCommand {
	protected AbstractHttpCommand(String name, String description) {
		super(name, description);
	}

	protected HttpResponse<JsonNode> get(String url) {
		return Unirest.get(url).asJson();
	}
}
