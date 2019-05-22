package com.qian.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-5 下午4:00:17
 * @Description
 */
public class MD5Utils {

	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		
		return sBuffer.toString();
		
	}

	public static String getMD5Code(String strObj) {
		
		String resultString = null;
		try {
			
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				resultString = byteToString(md.digest(strObj.getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			
		}
		
		return resultString;
		
	}
}
