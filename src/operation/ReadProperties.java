package operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

	Properties obj = new Properties();
	Properties config = new Properties();
	Properties data = new Properties();

	Properties allProperties = new Properties();

	// Properties CONFIG = new Properties();

	public Properties getObjectRepository() throws IOException {
		// Read object repository file
		InputStream stream = new FileInputStream(new File(
				System.getProperty("user.dir")
						+ "/src/objects/object.properties"));

		obj.load(stream);
		return obj;
	}

	public Properties getConfigRepository() throws IOException {
		// Read object repository file
		InputStream stream = new FileInputStream(new File(
				System.getProperty("user.dir")
						+ "/src/objects/config.properties"));
		// load all objects

		config.load(stream);
		return config;
	}

	public Properties getDataRepository() throws IOException {
		// Read object repository file
		InputStream stream = new FileInputStream(
				new File(System.getProperty("user.dir")
						+ "/src/objects/or.properties"));
		// load all objects

		data.load(stream);
		return data;
	}

	public Properties getAllRepository() throws IOException {
		// Read object repository file

		ReadProperties objects = new ReadProperties();
		Properties object = objects.getObjectRepository();
		Properties config = objects.getConfigRepository();
		Properties data = objects.getDataRepository();
		allProperties.putAll(object);
		allProperties.putAll(config);
		allProperties.putAll(data);
		return allProperties;
	}
}
