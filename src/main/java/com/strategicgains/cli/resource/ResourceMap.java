package com.strategicgains.cli.resource;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.strategicgains.cli.util.HttpMethod;

public class ResourceMap {
	private Map<String, Map<HttpMethod, String>> urlsByResourceByMethod = new ConcurrentHashMap<>();

	public String getUrl(String resource, HttpMethod method) {
		Map<HttpMethod, String> urlsByResource = urlsByResourceByMethod.get(resource);
		return (urlsByResource != null ? urlsByResource.get(method) : null);
	}

	public Collection<String> getResources() {
		return urlsByResourceByMethod.keySet();
	}

	public Collection<HttpMethod> getMethods(String resource) {
		Map<HttpMethod, String> urlsByResource = urlsByResourceByMethod.get(resource);
		return (urlsByResource != null ? urlsByResource.keySet() : null);
	}

	public void add(String resource, HttpMethod method, String url) {
		Map<HttpMethod, String> urlsByResource = urlsByResourceByMethod.computeIfAbsent(resource, k -> new ConcurrentHashMap<>());
		urlsByResource.put(method, url);
	}

	public void remove(String resource, HttpMethod method) {
		Map<HttpMethod, String> urlsByResource = urlsByResourceByMethod.get(resource);

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
