package cn.smart.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Ye
 */
public class DateUtil extends DateUtils {

    private static Logger logger = LogManager.getLogger(DateUtil.class);

    private DateUtil() {
    }

    public static final String format(Object date) {
        return format(date, "yyyy-MM-dd");
    }

    public static final String format(Object date, String pattern) {
        if (date == null) {
            return null;
        } else {
            return pattern == null ? format(date) : (new SimpleDateFormat(pattern)).format(date);
        }
    }

    public static String[] getWeekDays(Date date, String patten) {
        String[] days = new String[7];
        if (date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                c.add(Calendar.DATE, -1);
            }
            for (int j = 0; j < Calendar.DAY_OF_WEEK; j++) {
                date = c.getTime();
                days[j] = DateUtil.format(date, patten);
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        return days;
    }

    public static Date timestamp2Data(Long time) {

        SimpleDateFormat sdf = new SimpleDateFormat(DatePatten.YYYY_MM_DD_HH_MM_SS, Locale.CHINA);
        String dateStr = sdf.format(new Date(time * 1000));
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 根据年月日计算年龄,birthTimeString:"1994-11-14"
    public static int getAgeFromBirthTime(String birthTimeString) {
        // 先截取到字符串中的年、月、日
        String strs[] = birthTimeString.trim().split("-");
        int selectYear = Integer.parseInt(strs[0]);
        int selectMonth = Integer.parseInt(strs[1]);
        int selectDay = Integer.parseInt(strs[2]);
        // 得到当前时间的年、月、日
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);

        // 用当前年月日减去生日年月日
        int yearMinus = yearNow - selectYear;
        int monthMinus = monthNow - selectMonth;
        int dayMinus = dayNow - selectDay;

        // 先大致赋值
        int age = yearMinus;
        // 选了未来的年份
        if (yearMinus < 0) {
            age = 0;
            // 同年的，要么为1，要么为0
        } else if (yearMinus == 0) {
            // 选了未来的月份
            if (monthMinus < 0) {
                age = 0;
                // 同月份的
            } else if (monthMinus == 0) {
                // 选了未来的日期
                if (dayMinus < 0) {
                    age = 0;
                } else if (dayMinus >= 0) {
                    age = 1;
                }
            } else if (monthMinus > 0) {
                age = 1;
            }
        } else if (yearMinus > 0) {
            // 当前月>生日月
            if (monthMinus < 0) {
                // 同月份的，再根据日期计算年龄
            } else if (monthMinus == 0) {
                if (dayMinus < 0) {
                } else if (dayMinus >= 0) {
                    age = age + 1;
                }
            } else if (monthMinus > 0) {
                age = age + 1;
            }
        }
        return age;
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
                return isBetween(parseDate(d, DatePatten.YYYY_MM_DD), parseDate(startDate, DatePatten.YYYY_MM_DD), parseDate(endDate, DatePatten.YYYY_MM_DD));
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

    public interface DatePatten {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    }

}
