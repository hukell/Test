package com.mb.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static String getYearMonthDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 用"."隔开年月日是为了避免2016年1月11日和2016年11月1日这种都返回2016111这种一样的数据
        return year + "." + month + "." + day;
    }

    /**
     * 得到传入时间的年份
     *
     * @param time
     * @return 年
     */
    public static int getYear(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 得到传入时间的月份
     *
     * @param time
     * @return 月
     */
    public static int getMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到这个时间戳在当月的号数
     *
     * @return 这个月的天数
     */
    public static int getDayOfMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到传入的时间戳是当天的第几个小时
     *
     * @param time
     * @return
     */
    public static int getHourOfDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到某年某月这个月的总天数
     *
     * @return 这个月的天数
     */
    public static int getAllDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 根据年月日获得时间戳
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long getDayTime(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));

        return calendar.getTimeInMillis();
    }

    /**
     * 返回当前时间天的毫秒值
     *
     * @return
     */
    public static long getDayTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String timeStr = sdf.format(time);
        try {
            Date date = sdf.parse(timeStr);
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return time;
    }

    /**
     * 根据时间戳得到年月日格式的时间
     *
     * @param time 需要格式化的时间戳
     * @return
     */
    public static String getDateFormatTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        return format.format(time);
    }

    /**
     * 根据时间戳得到年月日格式的时间
     *
     * @param time 需要格式化的时间戳
     * @return
     */
    public static String getFormatTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault());
        return format.format(time);
    }

}