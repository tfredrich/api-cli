package com.strategicgains.cli;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.strategicgains.cli.resource.ResourceMap;

public class Config {
	private static final String CONFIG_SUBDIR = ".apicli";
	private static final String CONFIG_FILE = "config";
	private static final String HISTORY_FILE = "history";
	private static final String RESOURCES_FILE = "resources";
	private static final String FILE_FORMAT_STRING = "%s/%s/%s"; // home, subdir, file

	private String clientId;
	private String clientSecret;
	private String iss;
//	private OpenAPI oas;
//	private Environments environments;
//	private History history;
//	private ResourceMap resources;

	public Config() {
		clientId = "";
		clientSecret = "";
		iss = "";
	}

	public Config(String clientId, String clientSecret, String iss) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.iss = iss;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	/**
	 * Load the configuration from the default location.
	 * 
	 * @return the configuration.
	 */
	public static Config load() throws Exception {
		String path = System.getenv("PWD");

		if (path == null) {
			path = System.getProperty("user.pwd");
		}

		Properties p = loadProperties(String.format(FILE_FORMAT_STRING, path, CONFIG_SUBDIR, CONFIG_FILE));
		return fromProperties(p);
	}

	private static Properties loadProperties(String fileLocation) throws IOException {
		try (FileReader reader = new FileReader(fileLocation)) {
			Properties p = new Properties();
			p.load(reader);
			return p;
		}
	}

	public static Config fromProperties(Properties p) {
		return new Config(p.getProperty("clientId"), p.getProperty("clientSecret"), p.getProperty("iss"));
	}
}
