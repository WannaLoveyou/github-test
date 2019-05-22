package com.qian.util;

import java.text.DecimalFormat;

/**
 * @author Lu Kongwen
 * @version Create time：2016-1-13 下午4:11:47
 * @Description
 */
public class DoubleUtils {

	private static DecimalFormat df = new DecimalFormat("#.00");
	
	private static DecimalFormat intDf = new DecimalFormat("######0");
	
	public static double saveTwoDecimal(double d){
		return Double.parseDouble(df.format(d));
	}
	
	public static int toInt(double d){
		return Integer.parseInt(intDf.format(d));
	}
	
	public static double calSaveTwoDecimal(Object o, double d) {

		return Double.parseDouble(df.format(Double.parseDouble(String.valueOf(o)) + d));
	}

	public static double minusSaveTwoDecimal(Object Eo, Object So) {

		return Double.parseDouble(df.format(Double.parseDouble(String.valueOf(Eo)) - Double.parseDouble(String.valueOf(So))));
	}
}
