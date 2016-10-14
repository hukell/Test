package com.mb.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by MagicBean on 2016/01/20 16:16:59
 */
public class DateUtil {

    private static String[] WEEK={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    public static long getTimeC(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date da = format.parse(time);
           return da.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 格式化时间，将毫秒转换为分:秒格式
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2) {
            min = "0" + time / (1000 * 60) + "";
        } else {
            min = time / (1000 * 60) + "";
        }
        if (sec.length() == 4) {
            sec = "0" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 3) {
            sec = "00" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 2) {
            sec = "000" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 1) {
            sec = "0000" + (time % (1000 * 60)) + "";
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
    /**
     * 格式化时间
     * @param time
     * @return
     */
    public static String formatDateTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(time==null ||"".equals(time)){
            return "";
        }
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar current = Calendar.getInstance();

        Calendar today = Calendar.getInstance();	//今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set( Calendar.HOUR_OF_DAY, 0);
        today.set( Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();	//昨天

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)-1);
        yesterday.set( Calendar.HOUR_OF_DAY, 0);
        yesterday.set( Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);


        Calendar tomorrow = Calendar.getInstance();	//昨天

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)+1);
        yesterday.set( Calendar.HOUR_OF_DAY, 0);
        yesterday.set( Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);
        current.setTime(date);

        if(current.after(today)&&current.before(tomorrow)){
            return "今天 "+time.split(" ")[1];
        }else if(current.before(today) && current.after(yesterday)){

            return "昨天 "+time.split(" ")[1];
        }else if (current.after(tomorrow)){
            return "明天 "+time.split(" ")[1];
        }
        else{
            int index = time.indexOf("-")+1;
            return time.substring(index, time.length());
        }
    }
    /**
     * 格式化时间（输出类似于 刚刚, 4分钟前, 一小时前, 昨天这样的时间）
     *
     * @param time    需要格式化的时间 如"2014-07-14 19:01:45"
     * @param pattern 输入参数time的时间格式 如:"yyyy-MM-dd HH:mm:ss"
     *                <p/>如果为空则默认使用"yyyy-MM-dd HH:mm:ss"格式
     * @return time为null，或者时间格式不匹配，输出空字符""
     */
    public static String formatDisplayTime(String time, String pattern) {
        String display = "";
        int tMin = 60 * 1000;
        int tHour = 60 * tMin;
        int tDay = 24 * tHour;

        if (time != null) {
            try {
                Date tDate = new SimpleDateFormat(pattern).parse(time);
                Date today = new Date();
                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - tDay);
                Date tomorrow = new Date(todayDf.parse(todayDf.format(today)).getTime()+tDay);
                Date aftertomorrow = new Date(tomorrow.getTime()+tDay);
                if (tDate != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - tDate.getTime();
                    if (tDate.before(thisYear)) {
                        display = new SimpleDateFormat("yyyy年MM月dd日").format(tDate);
                    } else {

                        if (tDate.after(tomorrow)&&tDate.before(aftertomorrow)) {
                            display = " 明天 "+DateToWeek(tDate);
                        } else if (tDate.after(beforeYes) && tDate.before(yesterday)) {
                            display = " 昨天 "+DateToWeek(tDate) ;
                        }else if (tDate.after(yesterday)&&tDate.before(tomorrow)){
                            display = " 今天 "+DateToWeek(tDate);
                        }
                        else {
                            display = " "+DateToWeek(tDate);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return display;
    }

    /**
     * 日期变量转成对应的星期字符串
     * @param date
     * @return
     */
    public static String DateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > 7) {
            return null;
        }

        return WEEK[dayIndex - 1];
    }

    /**
     * 将日期信息转换成今天、明天、后天、星期
     * @param date
     * @return
     */
    public static String getDateDetail(String date){
//        Calendar today = Calendar.getInstance();
//        Calendar target = Calendar.getInstance();
//
//        DateFormat df = new SimpleDateFormat(Config.DATE_FORMAT);
//        try {
//            today.setTime(df.parse(getNowDateToStr()));
//            today.set(Calendar.HOUR, 0);
//            today.set(Calendar.MINUTE, 0);
//            today.set(Calendar.SECOND, 0);
//            target.setTime(df.parse(date));
//            target.set(Calendar.HOUR, 0);
//            target.set(Calendar.MINUTE, 0);
//            target.set(Calendar.SECOND, 0);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return null;
//        }
//        long intervalMilli = target.getTimeInMillis() - today.getTimeInMillis();
//        int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
//        return showDateDetail(xcts,target);
return "";
    }
    /**
     * 将日期差显示为日期或者星期
     * @param xcts
     * @param target
     * @return
     */
    private static String showDateDetail(int xcts, Calendar target){
        switch(xcts){
            case 0:
                return Constants.TODAY;
            case 1:
                return Constants.TOMORROW;
            case 2:
                return Constants.AFTER_TOMORROW;
            case -1:
                return Constants.YESTERDAY;
            case -2:
                return Constants.BEFORE_YESTERDAY;
            default:
                int dayForWeek = 0;
                dayForWeek = target.get(Calendar.DAY_OF_WEEK);
                switch(dayForWeek){
                    case 1: return Constants.SUNDAY;
                    case 2: return Constants.MONDAY;
                    case 3: return Constants.TUESDAY;
                    case 4: return Constants.WEDNESDAY;
                    case 5: return Constants.THURSDAY;
                    case 6: return Constants.FRIDAY;
                    case 7: return Constants.SATURDAY;
                    default:return null;
                }

        }
    }

}
