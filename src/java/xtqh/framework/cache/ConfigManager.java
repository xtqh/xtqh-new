package xtqh.framework.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Yan Fugen
 *
 */

public class ConfigManager {

	public static final String SQLMANAGER_XML = "sqlManager";

	public static final String CONFIG_PROPERTIES = "config";

	public static Map<String, String> XML_CACHEMAP = new HashMap<String, String>();

	public static Map<String, String> PROPERTIES_CACHEMAP = new HashMap<String, String>();

	/**
	 * 读取properties文件属性
	 * 
	 * @param function_id
	 * @param propertyId
	 * @return
	 */
	public String getConfigPropertyValue(String propertiesKey) {
		String result = null;
		String key = null;
		if (null != propertiesKey && !"".equals(propertiesKey.trim())) {
			key = propertiesKey.trim();
		}
		if (null != key) {
			result = PROPERTIES_CACHEMAP	.get(key);
		}

		return result;

	}

	/**
	 * 读取配置的sql
	 * 
	 * @param function_id
	 * @param propertyId
	 * @return
	 */
	public String getSqlFromConfig(String function_id, String propertyId) {
		String result = null;
		String key = null;
		if (null != function_id && !"".equals(function_id.trim()) && null != propertyId
				&& !"".equals(propertyId.trim())) {
			key = SQLMANAGER_XML + "_" + function_id.trim() + "_" + propertyId.trim();
		}
		if (null != key) {
			result = XML_CACHEMAP.get(key);
		}

		return result;
	}

}
