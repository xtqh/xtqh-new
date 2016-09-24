
package xtqh.framework.i18n;

import java.util.ResourceBundle;

/**
 * 
 * 
 * @ClassName: ResourceUtil
 * 
 * @Description: TODO
 * 
 * @author Comsys-Yan Fugen
 * 
 * @date Sep 13, 2016 4:54:50 PM
 *
 * 
 */
public class ResourceUtil {

	/**
	 * for i18n
	 */
	private static String CONFIG_FILE = "i18n";

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
