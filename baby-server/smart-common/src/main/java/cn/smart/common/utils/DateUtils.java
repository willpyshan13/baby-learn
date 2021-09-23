package cn.smart.common.utils;

import cn.smart.common.exception.BaseRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间工具类
 * 
 * @since 2015-03-04
 * */
public class DateUtils {

	/** 用于日志记录的Logger */
	private static final Logger log = LogManager.getLogger(DateUtils.class);

	private static String defaultDatePattern = "yyyyMMdd";

	private static String getDatePattern() {
		return defaultDatePattern;
	}

	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	public static String format(Date date) {
		return date == null ? "" : format(date, getDatePattern());
	}

	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate,
				getDatePattern());
	}

	public static Date parse(String strDate, String pattern)
			throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
				pattern).parse(strDate);
	}

	/**
	 * CTS 日期转换
	 * @param date date
	 * @return date
	 */
	public static Date CSTConvert(String date) {
		if (date == null || date.trim().length() == 0) {
			return null;
		}
		try {
			SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.UK);
			return df.parse(date.trim());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 添加日期 (月)
	 * 
	 * @param date
	 *            date
	 * @param n flag
	 * @return Date
	 * */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, n);
		return cal.getTime();
	}

	/**
	 * 获取每个月的最后一天
	 * 
	 * @param flag
	 *            year 当前年份
	 * @param month
	 *            month 当前月
	 * @param year flag
	 * @return Date
	 * */
	public static Date getLastDayOfMonth(String year, String month, int flag) {

		Calendar cal = Calendar.getInstance();
		cal.set(1, Integer.parseInt(year));
		cal.set(2, Integer.parseInt(month) - 1);
		cal.set(5, 1);
		cal.add(2, flag);
		cal.add(5, -1);
		return cal.getTime();
	}

	/**
	 * 获取每个月的最后一天
	 * 
	 * @param date
	 *            date
	 * @param flag flag
	 * @return Date
	 * */
	public static Date getLastDayOfMonth(Date date, int flag) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(5, 1);
		cal.add(2, flag);
		cal.add(5, -1);
		return cal.getTime();
	}

	/**
	 * 判断该日期是否为T+n
	 * 
	 * @since
	 * @param date
	 *            yyyyMMdd
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static int compareDate(String date, String format, int days) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date, format));
		cal.add(Calendar.DATE, days);
		return DateUtils.format(cal.getTime(), "yyyyMMdd").compareTo(
				DateUtils.format(new Date(), "yyyyMMdd"));
	}

	/**
	 * 获取每周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(7, 2);
		cal.add(5, -1);
		return cal.getTime();
	}

	/**
	 * 添加日期 (天)
	 * 
	 * @param date
	 *            date
	 * @param n flag
	 * @return Date
	 * */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 获取日期的前几天
	 *
	 * @param date
	 *            date
	 * @param n flag
	 * @return Date
	 * */
	public static Date reduceDay(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -n);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取日期的前一天
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
	/**
	 * add by yuwm
	 * 添加日期 (小时)
	 * @return Date
	 * */
	public static Date addHours(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, n);
		return cal.getTime();
	}

	/**
	 * add by yuwm
	 * 添加日期 (分钟)
	 * @return Date
	 * */
	public static Date addMinutes(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}


	/**
	 * add by yuwm
	 * 添加日期 (秒)
	 * @return Date
	 * */
	public static Date addSeconds(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, n);
		return cal.getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return String
	 * */
	public static String GetCurrentTime() {

		StringBuffer result = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		int h = calendar.get(11);
		int m = calendar.get(12);
		int s = calendar.get(13);
		if (h < 10) {
			result.append("0");
		}
		result.append(h);
		if (m < 10) {
			result.append("0");
		}
		result.append(m);
		if (s < 10) {
			result.append("0");
		}
		result.append(s);
		return result.toString();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return String yyyyMMddHHmmss
	 * */
	public static String getCurrDateTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 获取当前月份
	 * @return
	 * @author Tuyong
	 */
	public static String getCurrentMonth(){
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMM");
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * 获取上个月月份
	 * @return
	 * @author tuyong
	 */
	public static String getLastMonth(){
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMM");
	    String s=outFormat.format(cal.getTime());
	    return s;
	}
	/**
	 * 获取下个月月份
	 * @return
	 * @author tuyong
	 */
	public static String getNextMonth(){
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMM");
		String s=outFormat.format(cal.getTime());
		return s;
	}
	
	/**
	 * 获取上个月第一天
	 * @return
	 * @author Tuyong
	 */
	public static String getLashMonthFirstDay(){
		Calendar cal =Calendar.getInstance();
		int month=cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date strDateTo=cal.getTime();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMdd");
		String s = outFormat.format(strDateTo);
		return s;
	}
	
	/**
	 * 获取上个月最后一天
	 * @return
	 * @author Tuyong
	 */
	
	public static String getLashMonthLastDay(){
		Calendar cal =Calendar.getInstance();
		int month=cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date strDateTo=cal.getTime();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMdd");
		String s = outFormat.format(strDateTo);
		return s;
	}
	/**
	 * 格式化时间 yyyy-MM-dd
	 * 
	 * @return String yyyy-MM-dd
	 * */
	public static String formatDate(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
		return outFormat.format(date);
	}

	/**
	 * 格式化时间 yyyy/MM/dd HH:mm:ss
	 * 
	 * @return String yyyy/MM/dd HH:mm:ss
	 * */
	public static String formatDateTime(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return outFormat.format(date);
	}

	/**
	 * 格式化时间 yyyy/MM/dd"
	 * 
	 * @return String yyyy/MM/dd"
	 * */
	public static String formatDate2(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化时间 MM-dd HH:mm
	 * 
	 * @return String MM-dd HH:mm
	 * */
	public static String formatDate3(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化时间 yyyyMMdd
	 * 
	 * @return String yyyyMMdd
	 * */
	public static String formatDate4(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 获取前一天 Date
	 * */
	public static Date getLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取昨天
	 * @return
	 */
	public static String getYesterDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		return yesterday;
	}

	public static String formatDate5(Date myDate) {
		String strDate = getYear(myDate) + "-" + getMonth(myDate) + "-"
				+ getDay(myDate);
		return strDate;
	}

	public static String formatDate6(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String formatDate7(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化时间 yyyy.MM.dd HH:mm
	 * 
	 * @return String yyyy.MM.dd HH:mm
	 * */
	public static String formatDate8(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化时间 yyyy.MM.dd HH:mm:ss
	 * 
	 * @return String yyyy.MM.dd HH:mm:ss
	 * */
	public static String formatDate9(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static long Date2Long(int year, int month, int date) {
		Calendar cld = Calendar.getInstance();
		month--;
		cld.set(year, month, date);
		return cld.getTime().getTime();
	}

	public static long Time2Long(int year, int month, int date, int hour,
			int minute, int second) {
		Calendar cld = Calendar.getInstance();
		month--;
		cld.set(year, month, date, hour, minute, second);
		return cld.getTime().getTime();
	}

	public static int getYear(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L) {
			cld.setTime(new Date(t));
		}
		return cld.get(1);
	}

	public static int getMonth(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L) {
			cld.setTime(new Date(t));
		}
		return cld.get(2) + 1;
	}

	public static int getDay(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L) {
			cld.setTime(new Date(t));
		}
		return cld.get(5);
	}

	public static int getHour(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L) {
			cld.setTime(new Date(t));
		}
		return cld.get(11);
	}

	public static int getMinute(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L) {
			cld.setTime(new Date(t));
		}
		return cld.get(12);
	}

	public static int getSecond(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L) {
			cld.setTime(new Date(t));
		}
		return cld.get(13);
	}

	public static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(1);
	}

	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(2) + 1;
	}

	public static int getDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(5);
	}

	public static int getHour(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(11);
	}

	public static int getMinute(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(12);
	}

	public static int getSecond(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(13);
	}

	public static int getYear() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(1);
	}

	public static int getMonth() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(2) + 1;
	}

	public static int getDay() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(5);
	}

	public static String formatDate(String date) {
		if ((date != null) && (date.trim().length() >= 8)) {
			return date.substring(0, 4) + "/" + date.substring(4, 6) + "/"
					+ date.substring(6, 8);
		}
		return date;
	}

	public static String formatTime(String time) {
		if ((time != null) && (time.trim().length() >= 6)) {
			return time.substring(0, 2) + ":" + time.substring(2, 4) + ":"
					+ time.substring(4, 6);
		}
		return time;
	}

	public static String formateTimeOfDay(String time) {
		if ((time != null) && (time.trim().length() >= 12)) {
			return time.substring(0, 4) + "/" + time.substring(4, 6) + "/"
					+ time.substring(6, 8) + " " + time.substring(8, 10) + "："
					+ time.substring(10, 12);
		}
		return time;
	}

	public static int getDiffDate(Date date, Date date1) {
		return (int) ((date.getTime() - date1.getTime()) / 86400000L);
	}

	public static long getDiffTime(Date date, Date date1) {
		return (date.getTime() - date1.getTime()) / 1000L;
	}

	public static String getDateFromyyyyMMddHHmmss(String time) {
		return time.substring(0, 8);
	}

	public static String getTimeFromyyyyMMddHHmmss(String time) {
		return time.substring(8, 14);
	}

	/**
	 * 判断某日期是否在指定日期区间内
	 * 
	 * @param d
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isBetweenOfDefaultDatePattern(String d,
			String startDate, String endDate) {
		if (StringUtils.isBlank(d) || StringUtils.isBlank(startDate)
				|| StringUtils.isBlank(endDate)) {
			return false;
		} else {
			try {
				return isBetween(parse(d), parse(startDate), parse(endDate));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * 判断某日期是否在指定日期区间内
	 * 
	 * @param d
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isBetween(Date d, Date startDate, Date endDate) {
		if (d == null || startDate == null || endDate == null) {
			return false;
		} else {
			return (d.compareTo(startDate) >= 0 && d.compareTo(endDate) <= 0);
		}
	}

	/**
	 * 根据传入日期得到前一天日期
	 * 
	 * @param curDate
	 *            yyyyMMdd
	 * @return yyyyMMdd
	 * @throws ParseException
	 */
	public static String getLastDt(String curDate) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse(curDate));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date ate = calendar.getTime();
		return formatDate4(ate);
	}

	/**
	 * 根据传入日期得到明天日期
	 * 
	 * @param curDate
	 *            yyyy-MM-dd
	 * @return yyyy-MM-dd
	 * @throws ParseException
	 */
	public static String getNextDt(String curDate) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse(curDate, "yyyy-MM-dd"));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date ate = calendar.getTime();
		return formatDate(ate);
	}

	/**
	 * 日期格式装换
	 * 
	 * @param strDate
	 *            yyyyMMddHHmm
	 * @return yyyy-MM-dd HH:mm
	 * @throws ParseException
	 */
	public static String changStrDt(String strDate) {
		try {
			if (StringUtils.isNotBlank(strDate)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
				Date date = sdf.parse(strDate);
				return formatDate6(date);
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new BaseRuntimeException(e);
		}
		return strDate;
	}

	/**
	 * 日期格式装换
	 * 
	 * @param strDate
	 *            yyyyMMddHHmmss
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 */
	public static String changStrDt2(String strDate) {
		try {
			if (StringUtils.isNotBlank(strDate)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = sdf.parse(strDate);
				return formatDate7(date);
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new BaseRuntimeException(e);
		}
		return strDate;
	}

	/**
	 * 日期格式装换
	 * 
	 * @param strDate
	 *            yyyyMMddHHmmss
	 * @return yyyy-MM-dd
	 * @throws ParseException
	 */
	public static String changStrDt3(String strDate) {
		try {
			if (StringUtils.isNotBlank(strDate)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = sdf.parse(strDate);
				return formatDate(date);
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new BaseRuntimeException(e);
		}
		return strDate;
	}

	/**
	 * 日期格式装换
	 * 
	 * @param strDate
	 *            yyyyMMddHHmmss
	 * @return yyyy.MM.dd HH:mm
	 */
	public static String changStrDt4(String strDate) {
		try {
			if (StringUtils.isNotBlank(strDate)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = sdf.parse(strDate);
				return formatDate8(date);
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new BaseRuntimeException(e);
		}
		return strDate;
	}

	/**
	 * 日期格式装换
	 * 
	 * @param strDate
	 *            yyyyMMddHHmmss
	 * @return yyyy.MM.dd HH:mm:ss
	 */
	public static String changStrDt5(String strDate) {
		try {
			if (StringUtils.isNotBlank(strDate)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = sdf.parse(strDate);
				return formatDate9(date);
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new BaseRuntimeException(e);
		}
		return strDate;
	}

	/**
	 * 判断当前日期是否周末
	 * 
	 * @since
	 * @param date
	 * @return true：周末
	 * @throws ParseException
	 * @author wmchen
	 */
	public static boolean isWeekEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return true;
		return false;
	}

	/**
	 * 判断当前日期是否周末
	 * 
	 * @since
	 * @param date
	 *            yyyMMdd
	 * @return true：周末
	 * @throws ParseException
	 * @author wmchen
	 */
	public static boolean isWeekEnd(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return isWeekEnd(sdf.parse(date));
	}

	/**
	 * 判断是否为周末
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 6 || week == 0) { // 6为周六，0为周日
			return true;
		}
		return false;
	}

	/**
	 * 判断是否在当天指定时间段内
	 * 
	 * @param startHour
	 * @param startMin
	 * @param endHour
	 * @param endMin
	 * @return
	 */
	public static boolean isInDate(Integer startHour, Integer startMin, Integer endHour, Integer endMin) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, startHour);
		c.set(Calendar.MINUTE, startMin);
		c.set(Calendar.SECOND, 0);
		Date begin = c.getTime();

		c.set(Calendar.HOUR_OF_DAY, endHour);
		c.set(Calendar.MINUTE, endMin);
		c.set(Calendar.SECOND, 0);
		Date end = c.getTime();

		Date date = new Date();
		if (date.after(begin) && date.before(end)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否在工作日的指定时间内
	 * 
	 * @param date
	 * @param startHour
	 * @param startMin
	 * @param endHour
	 * @param endMin
	 * @return
	 */
	public static boolean isWeekDay(Date date, Integer startHour, Integer startMin, Integer endHour, Integer endMin) {
		if (isWeekend(date)) {
			return false;
		} else {
			if (isInDate(startHour, startMin, endHour, endMin)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 日期时间格式化
	 * 
	 * @since
	 * @param date
	 *            日期字符串
	 * @param fromFormat
	 *            原格式
	 * @param toFormat
	 *            转换后格式
	 * @return
	 * @throws ParseException
	 * @author wmchen
	 */
	public static String formatDateString(String date, String fromFormat, String toFormat) throws ParseException {
		return DateUtils.format(DateUtils.parse(date, fromFormat), toFormat);
	}

	/**
	 * 获取临近xDay日的日期
	 * 
	 * @since
	 * @param xDay
	 *            正整数或负整数
	 * @param format
	 *            返回的格式 （eg：yyyyMMdd）
	 * @return
	 * @author wmchen
	 */
	public static String getNearXDay(int xDay, String format) {
		Calendar cal = Calendar.getInstance();
		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear + xDay);
		return DateUtils.format(cal.getTime(), format);
	}

	/**
	 * 格式化当前日期
	 * 
	 * @since
	 * @param format
	 *            返回的格式 （eg：yyyyMMdd）
	 * @return
	 * @author wmchen
	 */
	public static String getCurrentDate(String format) {
		Calendar cal = Calendar.getInstance();
		return DateUtils.format(cal.getTime(), format);
	}

	/**
	 * 获取明天
	 * @param format
	 * @return
	 */
	public static String getNextDay(String format){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return  formatter.format(date);
	}

	/**
	 * 获取当月第X天
	 * 
	 * @since
	 * @param xDay
	 *            第X天
	 * @param format
	 *            返回的格式 （eg：yyyyMMdd）
	 * @return
	 * @author wmchen
	 */
	public static String getMonthXDay(int xDay, String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, xDay);
		return DateUtils.format(cal.getTime(), format);
	}

	/***
	 * 计算日期间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/***
	 * 计算日期间相差的秒数
	 * @param smdate 较小的时间
	 * @param bdate 较大的时间
	 * @return
	 * @throws ParseException
	 */
	public static int diffSenconds(String smdate, String bdate){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / 1000;

			return Integer.parseInt(String.valueOf(between_days));
		}  catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return 0;
	}

	/***
	 * 获取某一年的所有日期
	 * 
	 * @param year
	 * @return
	 * @throws ParseException
	 */
	public static String[] getDayForYear(int year) throws ParseException {

		String sdate = year + "0101";
		String edate = (year + 1) + "0101";
		int n = daysBetween(sdate, edate);
		String[] dates = new String[n];
		dates[0] = sdate;
		for (int i = 0; i < (n - 1); i++) {
			dates[i + 1] = getNextDt(dates[i]);
		}

		return dates;
	}
	
	/**
	 * 获取时间戳
	 * @param dateString
	 * @return
	 */
	public static String getTimeStamp(String dateString){
		try {
			if(StringUtils.isNotBlank(dateString)){
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				Date date = df.parse(dateString);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				long timestamp = cal.getTimeInMillis() / 1000;
				return Long.toString(timestamp);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取时间戳
	 * @param dateString
	 * @return
	 */
	public static String getTimeStamp(String dateString,String format){
		try {
			if(StringUtils.isNotBlank(dateString)){
				SimpleDateFormat df = new SimpleDateFormat(format);
				Date date = df.parse(dateString);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				long timestamp = cal.getTimeInMillis() / 1000;
				return Long.toString(timestamp);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间大小大小比较
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static int compareDate(Date dt1, Date dt2){
		if(dt1.getTime() > dt2.getTime())
			return 1;
		else if(dt1.getTime() < dt2.getTime())
			return 2;
		else
			return 0;
	}


	/**
	 * 获取问候语
	 * @return 早上好 or  下午好 or 晚上好
     */
	public static  String getGreetings() throws ParseException {
		String greetings = "";
		Date now = new Date();
		System.out.println(DateUtils.format(now,"yyyyMMddHHmmss"));
		Date startDate = DateUtils.parse(DateUtils.format(now,"yyyyMMdd060000"),"yyyyMMddHHmmss");
		Date endDate = DateUtils.parse(DateUtils.format(now,"yyyyMMdd120000"),"yyyyMMddHHmmss");
		if(DateUtils.isBetween(now,startDate,endDate)){
			greetings = "早上好";
		}else{
			startDate = DateUtils.parse(DateUtils.format(now,"yyyyMMdd120000"),"yyyyMMddHHmmss");
			endDate = DateUtils.parse(DateUtils.format(now,"yyyyMMdd180000"),"yyyyMMddHHmmss");
			if(DateUtils.isBetween(now,startDate,endDate)){
				greetings = "下午好";
			}else{
				greetings = "晚上好";
			}
		}
		return greetings;
	}

	public static void main(String[] arg) throws ParseException {
		/*
		 * String [] dates=getDayForYear(2015); String
		 * insetsql="INSERT INTO sys_holiday (SH_DATE, SH_TYPE, SH_REMARK) VALUES "
		 * ;//('2014', '0', NULL);"; System.out.println(dates.length); for
		 * (String string : dates) { if(isWeekEnd(string)){
		 * //System.out.println(string); System.out.println(insetsql+"('"+
		 * string +"', '1', NULL);");
		 * 
		 * }else{ System.out.println(insetsql+"('"+ string +"', '0', NULL);"); }
		 * 
		 * }
		 */
//		Date begin = DateUtils.parse("20160123070000", "yyyyMMddHHmmss");
//		if (new Date().before(begin)) { //签到未开始
//			System.out.println("早了");
//		}
		
		System.out.println(DateUtils.getNextDay("yyyyMMdd"));

	}
}
