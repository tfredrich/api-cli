package com.strategicgains.cli.resource;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceMap {
	private Map<String, Map<String, String>> urlsByResourceByMethod = new ConcurrentHashMap<>();

	public String getUrl(String resource, String method) {
		Map<String, String> urlsByResource = urlsByResourceByMethod.get(resource);
		return (urlsByResource != null ? urlsByResource.get(method) : null);
	}

	public Collection<String> getResources() {
		return urlsByResourceByMethod.keySet();
	}

	public Collection<String> getMethods(String resource) {
		Map<String, String> urlsByResource = urlsByResourceByMethod.get(resource);
		return (urlsByResource != null ? urlsByResource.keySet() : null);
	}

	public void add(String resource, String method, String url) {
		Map<String, String> urlsByResource = urlsByResourceByMethod.computeIfAbsent(resource, k -> new ConcurrentHashMap<>());
		urlsByResource.put(method, url);
	}

	public void remove(String resource, String method) {
		Map<String, String> urlsByResource = urlsByResourceByMethod.get(resource);
		if (urlsByResource != null) {
			urlsByResource.remove(method);
			if (urlsByResource.isEmpty()) {
				urlsByResourceByMethod.remove(resource);
			}
		}
	}

	public void remove(String resource) {
		urlsByResourceByMethod.remove(resource);
	}

	public void clear() {
		urlsByResourceByMethod.clear();
	}
}
