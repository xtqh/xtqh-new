package xtqh.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Bean类操作helper类。
 *
 * @author jim.yang@NineTowns.com
 * @since 1.0
 */
public abstract class BeanUtil {

	protected static Logger logger = Logger.getLogger(BeanUtil.class);

	/**
	 * Get a property of the object
	 * 
	 * @param object
	 *            An object
	 * @param propName
	 *            A property name of the object
	 * @return If the property is exists, its value will be returned,or null
	 *         instead.
	 */
	@SuppressWarnings("unchecked")
	public static final Object getProperty(Object object, String propName) {
		if (object == null)
			return null;
		Class clazz = object.getClass();
		// try to visit the get method
		String methodName = "get" + propName.substring(0, 1).toUpperCase() + propName.substring(1);
		try {
			Method getMethod = clazz.getMethod(methodName, new Class[0]);
			return getMethod.invoke(object, new Object[0]);
		} catch (Exception e) {
			// No such get method, the property is not visiable
			return null;
		}
	}

	/**
	 * 在对象之间拷贝值,如果source的值为空，则维持dest的旧值
	 * 
	 * @param dest
	 *            目标对象
	 * @param src
	 *            源对象
	 * @throws Exception
	 */
	public static void copyProperties(Object destination, Object source) {
		try {
			copyProperties(destination, source, true);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("copyProperties throws Exception " + e.toString());
			}
		}
	}

	public static void copyProperties(List<Object> dest, List<Object> src, boolean keepOnNull) throws Exception {
		if (null != src && !src.isEmpty()) {
			
		}
	}

	/**
	 * 在对象之间拷贝值.
	 * 
	 * @param dest
	 *            目标对象
	 * @param src
	 *            源对象
	 * @param keepOnNull
	 *            为true时，如果source的值为空，则维持dest的旧值
	 * @throws Exception
	 */
	public static void copyProperties(Object dest, Object src, boolean keepOnNull) throws Exception {
		if (src == null || dest == null)
			return;
		BeanInfo info = Introspector.getBeanInfo(src.getClass());
		PropertyDescriptor[] props = info.getPropertyDescriptors();
		for (int i = 0; i < props.length; i++) {
			PropertyDescriptor srcProp = props[i];
			PropertyDescriptor destProp = findProperty(dest, srcProp.getName());
			if (destProp != null && destProp.getWriteMethod() != null && srcProp.getReadMethod() != null) {
				try {
					Object srcVal = srcProp.getReadMethod().invoke(src, new Object[0]);
					Object destVal = destProp.getReadMethod().invoke(dest, new Object[0]);
					if (keepOnNull) {
						// If keepOnNull is open ,keep the old value if it exist
						if (dest != null && srcVal == null) {
							srcVal = destVal;
						}
					}
					// set the property only when the input is the same type as
					// the dest type
					destProp.getWriteMethod().invoke(dest, new Object[] { srcVal });
				} catch (Exception ex) {
					// if the property type are not matched, skip this property
				}
			}
		}
	}

	/**
	 * 查找对象属性.
	 * 
	 * @param object
	 * @param name
	 * @return
	 * @throws java.beans.IntrospectionException
	 */
	private static PropertyDescriptor findProperty(Object object, String name) throws IntrospectionException {
		BeanInfo info = Introspector.getBeanInfo(object.getClass());
		PropertyDescriptor[] properties = info.getPropertyDescriptors();
		for (int i = 0; i < properties.length; i++) {
			PropertyDescriptor property = properties[i];
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	public static void setProperty(Object object, String name, String value) {
		setProperty(object, name, value, "yyyy-MM-dd");
	}

	@SuppressWarnings("unchecked")
	public static void setProperty(Object object, String name, String value, String dateFormat) {
		try {
			PropertyDescriptor property = findProperty(object, name);

			Class propertyType = property.getPropertyType();

			if (propertyType == String.class) {
				property.getWriteMethod().invoke(object, new Object[] { value });
			}
			if (propertyType == Integer.class) {
				property.getWriteMethod().invoke(object, new Object[] { new Integer(value) });
			}
			if (propertyType == Long.class) {
				property.getWriteMethod().invoke(object, new Object[] { new Long(value) });
			}
			if (propertyType == Double.class) {
				property.getWriteMethod().invoke(object, new Object[] { new Double(value) });
			}
			if (propertyType == java.util.Date.class) {
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				Date date = format.parse(value);
				property.getWriteMethod().invoke(object, new Object[] { date });
			}
		} catch (Exception e) {
			System.out.println("Error name:" + name + "	value:" + value);
			e.printStackTrace();
		}
	}

	public static String bean2Sql(Object bean) {
		if (bean == null)
			return null;
		try {
			BeanInfo info = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] props = info.getPropertyDescriptors();
			List<String> fields = new ArrayList<String>();
			List<Object> values = new ArrayList<Object>();

			for (int i = 0; i < props.length; i++) {
				PropertyDescriptor srcProp = props[i];
				String field = srcProp.getName();
				if (field.equalsIgnoreCase("class"))
					continue;
				if (srcProp.getReadMethod() != null) {
					Object value = srcProp.getReadMethod().invoke(bean, new Object[0]);
					fields.add(field);
					values.add(value);
				}
			}
			System.out.println(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String args[]) {
		// TinsInspectXls xls = new TinsInspectXls();
		// BeanUtil.bean2Sql(xls);

	}
}
