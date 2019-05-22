package com.qian.util;

import java.util.Random;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-4 下午3:26:53
 * @Description
 */
public class MyRandomUtils {

	public static String get6bitNumber() {

		Random random = new Random();

		String result = "";

		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}

		return result;
	}
}
