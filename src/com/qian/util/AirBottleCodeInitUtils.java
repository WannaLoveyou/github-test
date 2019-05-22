package com.qian.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-23 下午4:31:33
 * @Description
 */
public class AirBottleCodeInitUtils {

	public static String sign = "code=";

	public static String initCode(String s) {

		int len = s.lastIndexOf(sign);

		if (len > 0) {
			s = s.substring(len + 5, s.length());
		}

		try {
			s = URLDecoder.decode(s.replaceAll("\\+", "%2B"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}

		String result = AES.decrypt(s);

		if (result == null) {
			return s;
		}

		return result;
	}

	public static List<Integer> getAirBottleIds(String airBottleIdStr) {

		List<Integer> result = new ArrayList<Integer>();

		String[] bottleIds = airBottleIdStr.split(",");

		for (String airBottleId : bottleIds) {
			
			if(StringUtils.nonEmpty(airBottleId)){
				result.add(Integer.parseInt(airBottleId));
			}

		}

		return result;
	}
}
