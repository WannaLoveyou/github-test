package com.qian.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-5 下午3:48:09
 * @Description
 */
public class SignUtils {

	private static Logger log = Logger.getLogger(SignUtils.class);

	public static final String weiXinApiKey = PathUtils.getProperty("wx.apikey");
	
	/**公众账号ID*/
	public static final String WeiXinAppid = PathUtils.getProperty("wx.appid");

	/**商户号*/
	public static final String WeiXinMchId = PathUtils.getProperty("wx.mchid");
	
	/**微信退款证书路径*/
	public static final String WeiXinCertPath = PathUtils.getProperty("wx.cert_path");

	
	/**退款URL*/
	public static final String WeiXinReturnMoneyUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	/**红包URL*/
	public static final String RedPacketUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";

	
	public static String weiXinSign(Map<String, Object> resultMap) {
		
		Map<String, Object> sortMap = sortMapByKey(resultMap);

		StringBuffer StringA = new StringBuffer();

		for (String key : sortMap.keySet()) {
			
			if (key.equals("sign")) {
				continue;
			}
			
			StringA.append(key + "=" + sortMap.get(key).toString() + "&");
		}

		StringA.append("key=" + weiXinApiKey);

		log.info(StringA);

		String sign = MD5Utils.getMD5Code(StringA.toString()).toUpperCase();

		log.info(sign);

		return sign;

	}

	public static Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {

		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}

		Map<String, Object> sortedMap = new TreeMap<String, Object>(new Comparator<String>() {
			public int compare(String key1, String key2) {
				return key1.compareTo(key2);
			}
		});

		sortedMap.putAll(oriMap);

		return sortedMap;
	}

	public static void main(String args[]) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("appid", "wxd930ea5d5a258f4f");
		map.put("mch_id", "10000100");
		map.put("device_info", "1000");
		map.put("body", "test");
		map.put("nonce_str", "ibuaiVcKdpRxkhJA");

		System.out.println(weiXinSign(map));

	}

}
