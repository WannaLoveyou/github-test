package com.qian.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @author Lu Kongwen
 * @version Create time：2016-11-5 下午2:21:15
 * @Description
 */
public class Base64Utils {

	// 加密
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	// 解密
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "gbk");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String args[]) throws IOException {
		
		String s = "短线大盘调整压力重现，2000点将再受考验；操作上，控仓位、不追高，继续轻仓防御，可关注创新大会。回复TD退订";
		
		System.out.println(getBase64(s));
		
		String d = "tKbA7dLss6NJbnZhbGlkIGxlbmd0aCBmb3IgYSBCYXNlLTY0IGNoYXIgYXJyYXku";
		
		System.out.println(getFromBase64(d));
	}
}
