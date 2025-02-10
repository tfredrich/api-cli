package com.strategicgains.cli.command;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.strategicgains.cli.Usage;
import com.strategicgains.cli.util.HttpMethod;

public class Resource extends AbstractHttpCommand {
	private static final String RESOURCE_NOT_FOUND = "Resource not found: ";

	private Map<String, ResourceUrls> resources = new HashMap<>();

	public Resource() {
		super("resource", "Manage resource types and their URLs.");
	}

	@Override
	public String execute(String[] args) {
		if (args.length < 2) {
			throw new IllegalArgumentException("missing resource command");
		}

		switch (args[1]) {
		case "add":
			return add(args);
		case "remove", "rm":
			return remove(args);
		case "list", "ls":
			if (args.length == 2) {
                list(null);
            }
			else if (args.length == 3) {
				list(args[2]);
			}
			break;
        default:
		}

		return null;
	}

	private String add(String[] args) {
		if (args.length < 5) {
			throw new IllegalArgumentException("missing resource-name, HTTP method, or URL");
		}

		return add(args[2], toMethod(args[3]), args[4]);
	}

	private String add(String resourceName, HttpMethod httpMethod, String resourceUrl) {
		ResourceUrls resource = resources.computeIfAbsent(resourceName, k -> new ResourceUrls());
		resource.put(httpMethod, resourceUrl);
		return String.format("Resource added: %s(%s)", resourceName, httpMethod);
	}

	private void list(String resourceName) {
		if (resources.isEmpty()) {
			System.out.println("No resources.");
		} else if (resourceName == null) {
			resources.forEach((name, resource) -> {
				System.out.println(name);
				resource.list();
            });
		} else {
			ResourceUrls resource = resources.get(resourceName);
			if (resource == null) {
				throw new IllegalArgumentException(RESOURCE_NOT_FOUND + resourceName);
			} else {
				System.out.println(resourceName);
				resource.list();
			}
		}
	}

	private String remove(String[] args) {
		if (args.length < 3) {
			throw new IllegalArgumentException("missing resource name");
		}

		if (args.length == 3) {
			return remove(args[2]);
		}

		return remove(args[2], toMethod(args[3]));
	}

	private String remove(String resourceName) {
		if (resources.remove(resourceName) == null) {
			throw new IllegalArgumentException(RESOURCE_NOT_FOUND + resourceName);
		}

		return String.format("Resource removed: %s", resourceName);
	}

	private String remove(String resourceName, HttpMethod method) {
		ResourceUrls resource = resources.get(resourceName);
        if (resource == null) {
            throw new IllegalArgumentException(RESOURCE_NOT_FOUND + resourceName);
        } else {
            if (resource.getUrl(method) == null) {
                throw new IllegalArgumentException("Method not found for resource: " + resourceName);
            } else {
                resource.remove(method);
            }

            if (resource.isEmpty()) {
            	resources.remove(resourceName);
            }
        }

		return String.format("Method removed: %s for method %s", resourceName, method);
	}

	@Override
	public Usage getUsage() {
		return super.getUsage().withOption("add <name> <http_method> <url>", "Add a resource type with a URL.")
				.withOption("remove <name> [method]", "Remove a resource type or per-method URL.")
				.withOption("list [name]", "List resource type(s) and their URLs.");
	}

	private HttpMethod toMethod(String method) {
		return HttpMethod.valueOf(method.toUpperCase());
	}

	/*
	 * A class to store the URLs for a resource type, indexed by HTTP method.
	 */
	private class ResourceUrls {
		private Map<HttpMethod, String> urlsByMethod = new EnumMap<>(HttpMethod.class);

		public void put(HttpMethod method, String url) {
			urlsByMethod.put(method, url);
		}

		public void remove(HttpMethod method) {
			urlsByMethod.remove(method);
		}

		public String getUrl(HttpMethod method) {
			return urlsByMethod.get(method);
		}

		public void list() {
			urlsByMethod.forEach((method, url) -> System.out.println("  " + method + " " + url));
		}

		public boolean isEmpty() {
			return urlsByMethod.isEmpty();
		}
	}
}
