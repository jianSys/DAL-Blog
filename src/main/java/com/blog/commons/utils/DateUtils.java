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
                }else{
                    return minutes+"分钟前";
                }
            }else {
                return hours + " 小时前";
            }
        }
        if (days < 0) {
            return "刚刚";
        }
        if (days < 1) {
            return days + "天前";
        } else {
            return sdf.format(date);
        }

    }
}
