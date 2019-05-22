package com.qian.util;

import java.util.UUID;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-7 下午5:04:43
 * @Description
 */
public class UniqueIDGenerator {

	public UniqueIDGenerator() {
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
