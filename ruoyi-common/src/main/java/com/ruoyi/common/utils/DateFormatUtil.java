package com.ruoyi.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: Administrator
 * @Date: 2020\3\20 0020
 */
public class DateFormatUtil {

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple = "yyyy-MM-dd";
    public static String FORMAT_YEAR = "yyyy";
    public static String FORMAT_MONTH = "MM";
    public static String FORMAT_DAY = "dd";
    public static String FORMAT_HOUR = "HH";
    public static String FORMAT_MI = "mm";
    public static String FORMAT_SEC = "ss";
    public static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_SDATETIME = "yyyy-MM-dd HH:mm";
    public static String FORMAT_DATE = "yyyy-MM-dd";
    public static String FORMAT_DATE1 = "yyyyMMdd";
    public static String FORMAT_STIME = "HH:mm";
    public static String FORMAT_TIME = "HH:mm:ss";
    public static String FORMAT_DATE_STR = "yyyy年MM月dd日";

    public static void main(String[] args) {
        System.out.println(getDaySub("20150708", "20151217","yyyyMMdd"));
        //System.out.println(getMonthSub("20150711","20160110")+1);
    }

    /**
     * 获取n天前/后的日期
     */
    public static String getAfterDate(String dateString,int n,String type,String dateFormat) {
        String resultDate = "";
        try {
            SimpleDateFormat spd = new SimpleDateFormat(dateFormat);
            Calendar calendar = new GregorianCalendar();
            Date date = spd.parse(dateString);
            calendar.setTime(date);
            if(type.equals("day")){
                calendar.add(calendar.DATE,n);//把日期往后增加一天.整数往后推,负数往前移动
            }
            if (type.equals("month")) {
                calendar.add(calendar.MONTH,n);//把日期往后增加一月.整数往后推,负数往前移动
            }

            date=calendar.getTime();
            resultDate = spd.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

    public static long getDaySub(String beginDateStr,String endDateStr,String dateFormat)
    {
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dateFormat);
        Date beginDate;
        Date endDate;
        try
        {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return day;
    }

    public static int getMonthSub(String beginDateStr,String endDateStr){
        int result = 0;
        try {
            SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
            Calendar c = Calendar.getInstance();

            c.setTime(sdf.parse(endDateStr));

            int year1 = c.get(Calendar.YEAR);
            int month1 = c.get(Calendar.MONTH);

            c.setTime(sdf.parse(beginDateStr));
            int year2 = c.get(Calendar.YEAR);
            int month2 = c.get(Calendar.MONTH);


            if(year1 == year2) {
                result = month1 - month2;
            } else {
                result = 12*(year1 - year2) + month1 - month2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

     /*转换前：String date = “2016-08-15T16:00:00.000Z”

    转换后：2016-08-16 00:00:00*/

    public String UTCStringtODefaultString(String UTCString) {
        try{
            UTCString = UTCString.replace("Z", " UTC");
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = utcFormat.parse(UTCString);
            return defaultFormat.format(date);
        } catch(ParseException pe)
        {
            pe.printStackTrace();
            return null;
        }
    }

    /**
     * 获取系统当前日期()，格式：yyyy-MM-dd
     *
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
    public static String formatDate(Date date, String format) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        if (format == null) {
            format = FORMAT_DATE;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }
        if (format == null) {
            format = FORMAT_DATE;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date parseDate(String datestr, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(datestr);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }return null;
    }

    private static GregorianCalendar getGregorianCalendar(Date date)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(2);
        calendar.setTime(date);
        return calendar;
    }

    public static Date getWeekFirstDate(Date date) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.set(7, 2);
        return calendar.getTime();
    }

    public static Date getWeekLastDate(Date date) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.set(7, 1);
        return calendar.getTime();
    }

    public static Date getMonthFirstDate(Date date) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getMonthLastDate(Date date) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static int getWeekNumber(Date date) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        return calendar.get(3);
    }

    public static int getWeek(Date date) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        return calendar.get(7);
    }

    public static int getMonth(Date date) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        return calendar.get(2);
    }

    public static Date addDateDay(Date date, int count) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.add(5, count);
        Date newdate = calendar.getTime();
        return newdate;
    }

    public static Date addDatetimeHour(Date date, int count) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.add(10, count);
        Date newdate = calendar.getTime();
        return newdate;
    }

    public static Date addDatetimeMinute(Date date, int count) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.add(12, count);
        Date newdate = calendar.getTime();

        return newdate;
    }

    public static Date addDateMonth(Date date, int count) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.add(2, count);
        Date newdate = calendar.getTime();
        return newdate;
    }

    public static Date addDateYear(Date date, int count) {
        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.add(1, count);
        Date newdate = calendar.getTime();
        return newdate;
    }

    public static long getDateDay(Date startDate, Date endDate)
    {
        Date startDay = parseDate(format(startDate, null), FORMAT_DATE);
        Date endDay = parseDate(format(endDate, null), FORMAT_DATE);

        long day = (startDay.getTime() - endDay.getTime()) / 86400000L;
        return day;
    }

    public static float getIntervalHour(Date startDate, Date endDate)
    {
        return (float)(endDate.getTime() - startDate.getTime()) / 3600000.0F;
    }

    /**
     * 将html5 date格式2017-09-11T12:30  格式化为标准的日期格式
     * @param datetime
     * @return
     */
    public static String dateFormat(String datetime){
        String date = "";
        date = datetime.replace("T", " ");
        date = date+":00";
        return date;
    }

    /**
     * 将数据库中的日期转换为html5能够识别的格式：2017-09-21T12:00
     * @param datetime
     * @return
     */
    public static String dateToString(String datetime) {
        String date = "";
        date = datetime.substring(0,10)+"T"+datetime.substring(11,16);
        return date;
    }

    /**
     * 获取某段时这里写代码片间内的所有日期
     * @param _dBegin
     * @param _dEnd
     * @return
     */
    public static List<String> findDates(String _dBegin, String _dEnd) {
        List<Date> lDate = new ArrayList<Date>();
        List<String> dateList = new ArrayList<>();
        Date dBegin = DateFormatUtil.parseDate(_dBegin, DateFormatUtil.FORMAT_DATE);
        Date dEnd = DateFormatUtil.parseDate(_dEnd, DateFormatUtil.FORMAT_DATE);
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))  {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        for(int i=0;i<lDate.size();i++){
            Date date = lDate.get(i);
            String _date = DateFormatUtil.format(date, DateFormatUtil.FORMAT_DATE);
            dateList.add(_date);
        }
        return dateList;
    }
    /**
     * 在给定的日期加上或减去指定月份后的日期
     *
     * @param sourceDate 原始时间
     * @param month      要调整的月份，向前为负数，向后为正数
     * @return
     */
    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);

        return c.getTime();
    }
}
