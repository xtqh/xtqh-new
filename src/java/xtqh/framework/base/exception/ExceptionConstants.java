package xtqh.framework.base.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExceptionConstants {
	public static final String Code_0000 = "0000";
	public static final String Msg_0000 = "SUCCESS";

	public static final String Code_0001 = "0001";
	public static final String Msg_0001 = "ERROR";
	
	public static final String Code_0002 = "0002";
	public static final String Msg_0002 = "";
	
	public static final String Code_0003 = "0003";
	public static final String Msg_0003 = "";

	public static final String Code_0004 = "0004";
	public static final String Msg_0004 = "WARNING";

	private static Map<String, String> returnCodeMap = new ConcurrentHashMap<String, String>();

	public static Map<String, String> getReturnCodeMap() {
		if (returnCodeMap.isEmpty()) {
			returnCodeMap.put(Code_0000, Msg_0000);
			returnCodeMap.put(Code_0001, Msg_0001);
			returnCodeMap.put(Code_0002, Msg_0002);
			returnCodeMap.put(Code_0003, Msg_0003);
			returnCodeMap.put(Code_0004, Msg_0004);
		}
		return returnCodeMap;
	}
}
