package com.wdzsj.cmn.util;

import org.apache.commons.lang.StringUtils;

public class CronUtil {
	
	private static String[] WEEK_EXP_ARR = {"*","MON","TUES","WED","THUR","FRI","SAT","SUN","MON-FRI"};
	
	/**
	 * 通过周几+时分秒获取Cron表达式
	 * @param cronCycle 周几(eg:SUN)
	 * @param cronHms 时分秒(eg:14:34:55)
	 * @return Cron表达式
	 */
	public static String getCron(String weekIndex,String cronHms){
		/**
		  	(周六日 ==> SUN-SAT)
			(周一至周五 ==> MON-FRI)
			
			MON TUES WEB THUR FRI SAT SUN
			
			每周一 12:23:45 ==> 45 23 12 ? * MON
			
			周一至周五 12:23:45 ==> 45 23 12 ? * MON-FRI
			
			每日 12:23:45 ==> 45 23 12 ? * *
			
			<option value="01">每日</option>
			<option value="02">周一</option>
			<option value="03">周二</option>
			<option value="04">周三</option>
			<option value="05">周四</option>
			<option value="06">周五</option>
			<option value="07">周六</option>
			<option value="08">周日</option>
			<option value="09">周一至周五</option>
			
		 */
		String cron = "0 0 0 * * ? *";//默认每天晚上12点执行
		if(StringUtils.isNotBlank(weekIndex) && StringUtils.isNotBlank(cronHms)){
			//时分秒分解
			String[] hmsArr = cronHms.split(":");
			int iWeekIndex = 0;
			try {
				iWeekIndex = Integer.parseInt(weekIndex);
				if(null != hmsArr && hmsArr.length == 3 && iWeekIndex <= WEEK_EXP_ARR.length){
					if(iWeekIndex > 0) --iWeekIndex;
					else if(iWeekIndex < 0) iWeekIndex = 0;
					cron = Integer.parseInt(hmsArr[2].trim()) + " " + Integer.parseInt(hmsArr[1].trim()) + " " + Integer.parseInt(hmsArr[0].trim()) + " ";
					cron += ("? * " + WEEK_EXP_ARR[iWeekIndex]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cron;
	}
	
}
