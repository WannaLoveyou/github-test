package com.qian.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-5 下午2:46:20
 * @Description
 */
public class SendSMSUtils {

	public static void send(String mobile, String text) {

		if (!checkMobilePhone(mobile)) {
			return;
		}

		String url = "http://59.50.95.232:8050/SendSms2014.aspx?";
		StringBuffer sb = new StringBuffer();
		sb.append("spid=").append("102905");
		sb.append("&pwd=").append("102905369");
		sb.append("&mobiles=").append(mobile);
		try {
			sb.append("&sms=").append(URLEncoder.encode(Base64Utils.getBase64(text), "GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpUtils.sendPost(url, sb.toString());
	}

	public static void sendHuMenResetPasswordBySMS(String mobile, String password) {

		if (!checkMobilePhone(mobile)) {
			return;
		}
		
		String url = "http://59.50.95.232:8050/SendSms2014.aspx?";
		StringBuffer sb = new StringBuffer();
		sb.append("spid=").append("102905");
		sb.append("&pwd=").append("102905369");
		sb.append("&mobiles=").append(mobile);

		StringBuffer sms = new StringBuffer();
		sms.append("尊敬的客户，您已成功申请用户卡密码重置，密码为").append(password).append("，请勿向他人泄露，您可通过微信平台进行密码修改，感谢您的使用。");

		try {
			sb.append("&sms=").append(URLEncoder.encode(Base64Utils.getBase64(sms.toString()), "GBK"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpUtils.sendPost(url, sb.toString());

	}

	public static void sendHuMenOrderConfirmBySMS(String mobile, String date) {

		if (!checkMobilePhone(mobile)) {
			return;
		}
				
		String url = "http://59.50.95.232:8050/SendSms2014.aspx?";
		StringBuffer sb = new StringBuffer();
		sb.append("spid=").append("102905");
		sb.append("&pwd=").append("102905369");
		sb.append("&mobiles=").append(mobile);

		StringBuffer sms = new StringBuffer();
		sms.append("尊敬的客户，您于").append(date).append("通过85191111订购的液化气已送达。为了你的家居安全，请注意安全用气，保持室内通风，使用后请关紧阀门，如有疑问或抢修的，请拨打85191111。");

		try {
			sb.append("&sms=").append(URLEncoder.encode(Base64Utils.getBase64(sms.toString()), "GBK"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpUtils.sendPost(url, sb.toString());

	}

	public static int getHuMenSMSNum() {

		String url = "http://59.50.95.232:8050/GetBalance2013.aspx";
		StringBuffer sb = new StringBuffer();
		sb.append("spid=").append("102905");
		sb.append("&pwd=").append("102905369");

		String result = HttpUtils.sendPost(url, sb.toString());

		Map<String, Object> resultMap = JSONUtils.xmlToJSONFromHuMenSMS(result);

		int num = Integer.parseInt(String.valueOf(resultMap.get("Balance")));

		return num;
	}

	public static boolean checkMobilePhone(String mobile) {

		// 只发11位的电话号码
		if (mobile != null & mobile.length() == 11) {
			return true;
		}

		return false;
	}

	public static void main(String args[]) {
		sendHuMenOrderConfirmBySMS("13631472560", TimeUtils.getyyyyMMddHHmmStringByDate(new Date()));
	}
}
