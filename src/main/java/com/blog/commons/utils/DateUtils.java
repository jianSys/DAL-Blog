package com.blog.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: dal-blog
 * @description: 时间工具类
 * @author: jian
 * @create: 2021-06-28 23:40
 **/
public class DateUtils {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public static String getTimeAgo(Date date) {
        long nowTime = System.currentTimeMillis();
        long time = date.getTime();
        //获取时间差
        long between = nowTime - time;
        //向下取整获取天数
        long days = between / (24 * 3600 * 1000);
        if (days == 0) {
            //如果天数等于零计算小时数
            long hours = between % (24 * 3600 * 1000) / (3600 * 1000);
            if (hours==0){
                long minutes = between % (24 * 3600 * 1000) % (3600 * 1000)/(60 * 1000);
                if (minutes==0){
                    long sec = between % (24 * 3600 * 1000) % (3600 * 1000) % (60 * 1000) / 1000;
                    return sec+"秒前";
                }else{
                    return minutes+"分钟前";
                }
            }else {
                return hours + "小时前";
            }
        }else if (days < 0){
            return "刚刚";
        }else if (days<1){
            return days + "天前";
        }else{
            return sdf.format(date);
        }
    }

    /**
     * 时间转字符串date->2021-01-01
     * @param date
     * @return
     * @throws Exception
     */
    public static String parseDate2String(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        return parseDate2String(date, "yyyy-MM-dd");
    }

    /**
     * 日期转换 Date -> String
     *
     * @param date    Date类型信息
     * @param pattern 格式模板
     * @return 字符串时间
     * @throws Exception 抛出异常
     */
    public static String parseDate2String(Date date, String pattern) throws Exception {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String strDate = sdf.format(date);
        return strDate;
    }


}
