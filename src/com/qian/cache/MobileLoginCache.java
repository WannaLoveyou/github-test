package com.qian.cache;

import java.util.HashMap;
import java.util.Map;

import com.qian.code.LimitLoginCode;
import com.qian.entity.User;

/**
 * @author Lu Kongwen
 * @version Create time：2016-9-20 下午3:16:48
 * @Description
 */
public class MobileLoginCache {

	public static Map<Integer, String> AccountMap;

	public static void init() {
		AccountMap = new HashMap<Integer, String>();
	}

	public static boolean checkLimitLogin(User user, String deviceCode) {

		if (user.getLimit_login() == LimitLoginCode.NO_LIMIT_LOGIN) {
			return true;
		}

		String code = AccountMap.get(user.getId());

		if (code != null && (!code.equals(deviceCode))) {
			return false;
		}

		AccountMap.put(user.getId(), deviceCode);

		return true;
	}

	public static void clearLimitLogin(int userId) {
		AccountMap.put(userId, null);
	}
}
