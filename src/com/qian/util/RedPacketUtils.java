package com.qian.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author Lu Kongwen
 * @version Create time：2017-11-7 下午7:11:46
 * @Description
 */
public class RedPacketUtils {

	public static final Logger log = Logger.getLogger(RedPacketUtils.class);
	
	public static final String SEND_NAME = "富民燃气";//红包发送者名称
	
	public static final String IP = "106.75.137.43"; 
	
	public static final String ACT_NAME = "全民扫码领红包"; //活动名称
	
	public static final String WISHING = "感谢您参与富民燃气一周年庆活动，祝您生活愉快！ "; //祝福语

	public static final String REMARK = "拆取后即可获得现金红包"; //备注
	
	public static boolean sendWeiXinRedPacket(String openid,double money,String orderCode){

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nonce_str", UniqueIDGenerator.getUUID());
		map.put("mch_billno", orderCode);
		map.put("mch_id", SignUtils.WeiXinMchId);
		map.put("wxappid", SignUtils.WeiXinAppid);
		map.put("send_name", SEND_NAME);
		map.put("re_openid", openid);
		map.put("total_amount", (int)(money * 100)); //单位分
		map.put("total_num", 1); 
		map.put("wishing", WISHING); 
		map.put("client_ip", IP);
		map.put("act_name", ACT_NAME);
		map.put("remark", REMARK);
		map.put("sign", SignUtils.weiXinSign(map));

		
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append("<" + entry.getKey() + "><![CDATA[" + entry.getValue().toString() + "]]></" + entry.getKey() + ">");
		}
		sb.append("</xml>");

		log.info("-------------------------------------------------RedPacket------------------------------------------------");
		log.info(sb);
		log.info("-------------------------------------------------RedPacket------------------------------------------------");

		String weiXinResult = null;
		try {
			weiXinResult = HttpUtils.refundByWeiXin(SignUtils.RedPacketUrl, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("-------------------------------------------------RedPacketyResult------------------------------------------------");
		log.info(weiXinResult);
		log.info("-------------------------------------------------RedPacketResult------------------------------------------------");

		Map<String, Object> resultMap = JSONUtils.xmlToJSON(weiXinResult);

		if (!("SUCCESS".equals(resultMap.get("return_code").toString()))) {
			return false;
		}

		if (!("SUCCESS".equals(resultMap.get("result_code").toString()))) {
			return false;
		}

		return true;
	}
}
