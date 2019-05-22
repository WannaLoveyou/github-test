package com.qian.util;

import java.util.regex.Pattern;


/**
 * @author Lu Kongwen
 * @version Create time：2015-11-24 下午3:33:02
 * @Description
 */
public class StringUtils {

	public static boolean isEmpty(String s) {

		if (s == null || s.equals("")) {
			return true;
		}

		return false;
	}

	public static boolean nonEmpty(String s) {

		if (s == null || s.equals("")) {
			return false;
		}

		return true;
	}
	
	/**
	* 判断是否为整数
	* @param str 传入的字符串
	* @return 是整数返回true,否则返回false
	*/
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
	/**
	* 判断是否为数字
	* @param str 传入的字符串
	* @return 是整数返回true,否则返回false
	*/
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	/**
	* 判断是否为数字
	* @param str 传入的字符串
	* @return 是整数返回true,否则返回false
	*/
	public final static boolean isNumeric1(String s) { 
		if (s != null && !"".equals(s.trim())) 
			return s.matches("^[0-9]*$"); 
		else
			return false;
	}
	/**
	* 判断是否为数字 用ascii码 
	* @param str 传入的字符串
	* @return 是整数返回true,否则返回false
	*/
	public static boolean isNumericByAscii(String str){ 
		for(int i=str.length();--i>=0;){ 
			int chr=str.charAt(i); if(chr<48 || chr>57) return false;
		} 
		return true;
	}
	
}
