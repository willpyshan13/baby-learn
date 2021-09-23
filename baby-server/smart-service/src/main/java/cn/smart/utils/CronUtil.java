package cn.smart.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转 Cron 表达式
 * @author Ye
 */
public class CronUtil {

    private static final String DEFAULT_FORMAT = "ss mm HH dd MM ? yyyy";

    /**
     * 日期 转 Cron 表达式
     * @param date 日期
     * @return cron
     */
    public static String format(final Date date) {
        return formatWithPatten(date, DEFAULT_FORMAT);
    }

    public static String formatWithPatten(final Date date, String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        String cron = null;
        if (null != date) {
            cron = sdf.format(date);
        }
        return cron;
    }

    /**
     * 日期 转 Cron 表达式
     * @param dateStr date 字符串 yyyy-MM-dd HH:mm:ss
     * @return cron
     */
    public static String format(final String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cron;
        try {
            Date date = sdf.parse(dateStr);
            cron = format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return cron;
    }
}
