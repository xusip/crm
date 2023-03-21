package cn.edu.nncat.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-12 20:02
 * @version:1.0
 **/

/*
* 对date类数据进行处理的工具类
*
* */
public class DateUtils {
    /*
     * 对指定的date对象进行格式化：yyyy-MM-dd HH:mm:ss
     * @param date
     * */
    public static String formateDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /*
     * 对指定的date对象进行格式化：yyyy-MM-dd
     * @param date
     * */
    public static String formateDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /*
     * 对指定的date对象进行格式化：yyyy-MM-dd HH:mm:ss
     * @param date
     * */
    public static String formateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
