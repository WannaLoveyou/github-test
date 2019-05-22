package com.qian.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-24 下午3:13:31
 * @Description
 */
public class TimeUtils {

	private static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateTimesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat appointmentTimesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private static SimpleDateFormat rongXinTongDateSdf = new SimpleDateFormat("yyyyMMdd");

	public static final int oneDaySeconds = 24 * 60 * 60;

	public static final int oneMinSeconds = 60;

	public static boolean compareTimeByMin(Date time1, Date time2, int mins) {

		if ((time1.getTime() - time2.getTime()) / 1000 > mins * oneMinSeconds) {
			return true;
		}
		return false;
	}

	public static int compareTimeByYear(Date fDate, Date oDate) {

		int year = (int) Math.ceil(1.0 * compareTimeByMonth(fDate,oDate) / 12);

		return year;
	}
	
	public static int compareTimeByMonth(Date fDate, Date oDate) {

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(fDate);
		int fromYear = calendar1.get(Calendar.YEAR);
	    int fromMonth = calendar1.get(Calendar.MONTH);

	    Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(oDate);
	    int toYear = calendar2.get(Calendar.YEAR);
	    int toMonth = calendar2.get(Calendar.MONTH);

	    int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth) + 1;
	    
	    return month;
	}

	public static int compareTimeByDay(Date fDate, Date oDate) {

		long times = oDate.getTime() - fDate.getTime();

		return (int) (times / (oneDaySeconds * 1000));

	}

	public static String getNextDayString(String s) {
		return getAddDayString(s, 1);
	}

	public static String getFewDayString(String s, int day) {
		return getAddDayString(s, day);
	}

	private static String getAddDayString(String s, int day) {

		Date date = getDateByyyyyMMdd(s);

		long time = date.getTime();

		time = time + day * oneDaySeconds * 1000;

		return dateSdf.format(new Date(time));
	}

	public static Date getDateByyyyyMMdd(String s) {

		Date date = null;
		try {
			date = dateSdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String getyyyyMMddHHmmssStringByDate(Date date) {
		return dateTimesdf.format(date);
	}

	public static String getyyyyMMddHHmmStringByDate(Date date) {
		return appointmentTimesdf.format(date);
	}

	public static String getyyyyMMddStringByDate(Date date) {
		return dateSdf.format(date);
	}

	public static boolean compareTimeByyyyyMMddHHmm(String str1, String str2) {

		Date time1 = formatAppointmentTime(str1);
		Date time2 = formatAppointmentTime(str2);

		return time1.getTime() > time2.getTime();
	}

	public static long getDateTime(String s) {
		try {
			Date date = dateTimesdf.parse(s);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static String getRongXinTongStr(Date date) {
		return rongXinTongDateSdf.format(date);
	}

	public static Date formatAppointmentTime(String s) {

		if (!StringUtils.nonEmpty(s)) {
			return null;
		}

		Date date = null;

		try {
			date = appointmentTimesdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	public static long getDistanceMinutes(long time1, long time2) {

		long diff;

		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}

		long minutes = diff / (1000 * 60);

		return minutes;
	}

	public static void addFewYear(Calendar calendar, int yearNum) {

		calendar.add(Calendar.YEAR, yearNum);

	}

	public static Date addFewYear(Date date, int yearNum) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		addFewYear(calendar, yearNum);

		return calendar.getTime();
	}

	public static boolean compareOrderConform(Date date1, Date date2) {

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		int day1 = calendar1.get(Calendar.DAY_OF_YEAR);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		int day2 = calendar2.get(Calendar.DAY_OF_YEAR);

		if (day1 < day2) {
			return false;
		}

		return true;
	}

	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		try {
			dateSdf.setLenient(false);// 严格判断
			dateSdf.parse(str);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}
}
