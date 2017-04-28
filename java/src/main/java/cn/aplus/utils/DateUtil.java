package cn.aplus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private final static String DATE_TIME_COMPACT = "yyyyMMddHHmmssSSS";
	private final static String DATE_TIME = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 两个时间差
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long TimeDiffer(long startTime, long endTime) {

		long td = (startTime - endTime) / 1000;
		long returnDiffer = td;

		if (returnDiffer < 0)
			returnDiffer = 0 - returnDiffer;

		return returnDiffer;
	}

	/*
	 * DATE_TIME = "yyyyMMddHHmmss"
	 */
	public static synchronized String parseDatetimeCompact(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_COMPACT);
		return formatter.format(date);
	}
	/**
	 * DATE_TIME = "yyyyMMddHHmmss"
	 * @param date
	 * @return
	 */
	public static synchronized String parseDatetime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME);
		return formatter.format(date);
	}
	
	public static synchronized String parseDatetimeSelf(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	/**
	 * 两个时间相差天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int calLastedDay(Date startDate,Date endDate) {
		long a = endDate.getTime();
		long b = startDate.getTime();
		int c = (int) ((a - b) / (1000 * 60 * 60 * 24));
		return c;
	}
}
