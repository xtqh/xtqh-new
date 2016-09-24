/**
 * 
 */
package xtqh.framework.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import xtqh.framework.bean.CommonConstants;

/**
 * 
 * <code>DateUtils.java</code>
 * <p>
 * 
 * @function:日期工具类
 * 
 * @author liaoyingqiu
 * @date 2013-3-26 下午1:22:47
 * @version 1.0 </br>
 */
public class DateUtils {
	/**
	 * 
	 * @function:日期转换为字符串
	 * @author liaoyingqiu
	 * @param date
	 * @return
	 */
	public static String Date2Str(Date date) {
		return Date2Str(date, CommonConstants.DEFAULT_DATE_FORMAT);
	}

	/**
	 * 
	 * @function:日期转换为字符
	 * @author liaoyingqiu
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String Date2Str(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		String result = format.format(date);

		return result;

	}

	/**
	 * 
	 * @function:字符串转换为日期对象
	 * @author liaoyingqiu
	 * @param dateStr
	 * @return
	 */
	public static Date str2Date(String dateStr) {
		return str2Date(dateStr, CommonConstants.DEFAULT_DATE_FORMAT);

	}

	/**
	 * 
	 * @function:字符串转换为日期对象
	 * @author liaoyingqiu
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String dateStr, String pattern) {
		if (dateStr == null || dateStr.length() <= 0) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		try {
			Date date = format.parse(dateStr);
			return date;
		} catch (ParseException e) {
			return null;
		}

	}

	/**
	 * 
	 * @function:TimeStamp转换为字符串
	 * @author luo jun xiang
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static String timestamp2String(Timestamp time) {
		if (time == null) {
			return null;
		}
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			// 方法一
			tsStr = sdf.format(time);
			return tsStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 将omnibus中的时间值(UTC)转换成Date类型
	 * 
	 * @param utcValue
	 * @return
	 */
	public static Date UTCToDate(long utcValue) {
		Date date = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(1970, 0, 1, 8, 0, 0);
		long start = calendar.getTimeInMillis();
		long end = start + 1000 * utcValue;
		date = new Date(end);
		return date;
	}

	/**
	 * 
	 * @function:测试主函数入口
	 * @author liaoyingqiu
	 * @param args
	 */
	public static void main(String[] args) {
		Date current = new Date();
		String dateStr = Date2Str(current, CommonConstants.DATE_FORMAT_MINUTE);
		System.out.println(dateStr);

		long utcValue = 1420512440;
		System.out.println("utc date is:" + UTCToDate(utcValue));

	}
}
