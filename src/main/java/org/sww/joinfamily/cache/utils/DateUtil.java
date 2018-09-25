package org.sww.joinfamily.cache.utils;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 
	 * @author: Administrator
	 * @date: 2018年9月25日 下午7:53:25
	 * @Title: formatCurrentDate
	 * @Description: 返回当前系统时间
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getCurrentDateString() {
		String sysTimestamp = new Timestamp(System.currentTimeMillis()).toString();
		return sysTimestamp.substring(0, 10);
	}
	
}
