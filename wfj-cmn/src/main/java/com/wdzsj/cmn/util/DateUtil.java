package com.wdzsj.cmn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期操作工具类
 *
 * @author qy
 * @since 1.0
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 格式化日期与时间
     */
    public static String formatDatetime(Date datetime) {
    	if(null == datetime) return "";
        return datetimeFormat.format(datetime);
    }

    /**
     * 格式化日期
     */
    public static String formatDate(Date date) {
    	if(null == date) return "";
        return dateFormat.format(date);
    }

    /**
     * 格式化时间
     */
    public static String formatTime(Date date) {
    	if(null == date) return "";
        return timeFormat.format(date);
    }

    /**
     * 获取当前日期与时间
     */
    public static String getCurrentDatetime() {
        return datetimeFormat.format(new Date());
    }

    /**
     * 获取当前日期
     */
    public static String getCurrentDate() {
        return dateFormat.format(new Date());
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        return timeFormat.format(new Date());
    }

    /**
     * 解析日期与时间
     */
    public static Date parseDatetime(String str) {
    	if(StringUtil.isEmpty(str) || str.length() < 18) return null;
        Date date = null;
        try {
            date = datetimeFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期字符串出错！格式：yyyy-MM-dd HH:mm:ss", e);
        }
        return date;
    }

    /**
     * 解析日期
     */
    public static Date parseDate(String str) {
    	if(StringUtil.isEmpty(str)) return null;
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期字符串出错！格式：yyyy-MM-dd", e);
        }
        return date;
    }

    /**
     * 解析时间
     */
    public static Date parseTime(String str) {
    	if(StringUtil.isEmpty(str)) return null;
        Date date = null;
        try {
            date = timeFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期字符串出错！格式：HH:mm:ss", e);
        }
        return date;
    }
    
    /**
	 * 比对时间大小
	 * @param beginDate
	 * @param endDate
	 * @param date
	 * @return
	 */
	public static boolean dateCompare(Date beginDate,Date endDate){
		if(beginDate.getTime() <= endDate.getTime()) {
			return true;
		}
		return false;
	}
    
    /**
	 * 比对时间大小
	 * @param beginDate
	 * @param endDate
	 * @param date
	 * @return
	 */
	public static boolean dateCompare(Date beginDate,Date endDate,Date date){
		if(date.getTime() >= beginDate.getTime() && date.getTime() <= endDate.getTime()) {
			return true;
		}
		return false;
	}
	
	public static String trimLastZero(String dt){
		String result = null;
		if(StringUtils.isNotBlank(dt)){
			if(dt.endsWith(".0"))
				result = dt.substring(0,dt.length() - 2);
			else
				result = dt;
		}
		return result;
	}
	
}
