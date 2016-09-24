package xtqh.util;

import java.util.ResourceBundle;

/**
 * 
 * @author Yan Fugen
 *
 */
public class ConfigUtil {

	private String mysql_xtqh_username;
	/**
	 * for config property file
	 */
	private static String CONFIG_FILE = "config";

	private static ResourceBundle resourceBundle = null;

	private static void init() {
		resourceBundle = ResourceBundle.getBundle(CONFIG_FILE);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		if (resourceBundle == null) {
			init();
		}
		if (resourceBundle.containsKey(key)) {
			return resourceBundle.getString(key);
		} else {
			return key;
		}
	}
}
