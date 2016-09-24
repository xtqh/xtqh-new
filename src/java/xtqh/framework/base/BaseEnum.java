package xtqh.framework.base;

import java.io.IOException;
import java.util.Properties;

public class BaseEnum {
	private String propertyFilename = getClass().getSimpleName() + ".properties";
	private static Properties properties;

	private String value;

	private void init() throws IOException {
		if (properties == null) {
			properties = new Properties();
			properties.load(getClass().getResourceAsStream(propertyFilename));
			value = (String) properties.get(this.toString());
		}
	}

	public String getValue() throws IOException {
		if (value == null) {
			init();
		}
		return value;
	}
}
