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
	private boolean isDebug = false;

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

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	/**
	 * Load the configuration from the default location.
	 * @param isDebug true to print stacktraces on error. 
	 * @param string alternate configuration file location (optional).
	 * 
	 * @return the configuration.
	 */
	public static Config load(String configLocation, boolean isDebug)
	throws Exception {
		String fileLocation = null;
		
		if (configLocation != null) {
			fileLocation = configLocation;
		}
		else {
			String path = System.getenv("APICLI_CONFIG");

			if (fileLocation == null) {
				path = System.getProperty("user.dir");
			}

			fileLocation = String.format(FILE_FORMAT_STRING, path, CONFIG_SUBDIR, CONFIG_FILE);
		}

		try {
			return loadFromFile(fileLocation);
		}
		catch (IOException e) {
			if (isDebug) {
				e.printStackTrace();
			}

			return createDefaultConfig(fileLocation);
		}
	}

	private static Config createDefaultConfig(String fileLocation) {
		Properties p = new Properties();
		p.setProperty("clientId", "your-client-id");
		p.setProperty("clientSecret", "your-client-secret");
		p.setProperty("iss", "https://your-issuer.com");
		createFilesystemIfNeeded(fileLocation);
		writeProperties(fileLocation, p);
		return fromProperties(p);
	}

	private static Config loadFromFile(String fileLocation)
	throws IOException {
		Properties p = loadProperties(fileLocation);
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
