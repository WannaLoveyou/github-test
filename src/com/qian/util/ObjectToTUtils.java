package com.qian.util;

import java.util.Date;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-21 下午3:08:37
 * @Description
 */
public class ObjectToTUtils {

	public static Integer toInteger(Object o) {
		return o == null ? null : Integer.valueOf(o.toString());
	}

	public static String toString(Object o) {
		return o == null ? null : o.toString();
	}

	public static Double toDouble(Object o) {
		return o == null ? null : Double.valueOf(o.toString());
	}

	public static Date toDate(Object o) {
		return o == null ? null : new Date(Long.valueOf(o.toString()) * 1000);
	}
}
