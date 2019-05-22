package com.qian.util;

import com.qian.entity.ClientInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-10-31 下午3:49:34
 * @Description
 */
public class InitDataUtils {

	public static String findOrderNumber(ClientInfo clientInfo) {

		if (StringUtils.nonEmpty(clientInfo.getMobile_tel_number_1())) {
			return clientInfo.getMobile_tel_number_1();
		}

		if (StringUtils.nonEmpty(clientInfo.getMobile_tel_number_2())) {
			return clientInfo.getMobile_tel_number_2();
		}

		if (StringUtils.nonEmpty(clientInfo.getFixed_tel_number_1())) {
			return clientInfo.getFixed_tel_number_1();
		}

		if (StringUtils.nonEmpty(clientInfo.getFixed_tel_number_2())) {
			return clientInfo.getFixed_tel_number_2();
		}

		return null;
	}
}
