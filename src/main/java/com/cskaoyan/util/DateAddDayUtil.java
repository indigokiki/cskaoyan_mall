package com.cskaoyan.util;

import java.util.Date;

/**
 * @author YangShuo
 * @date 2019-07-09 16:36
 */
public class DateAddDayUtil {
    public static Date dateAddDay(Date date, int days){
        long dateTime = date.getTime();
        dateTime += 24L*60*60*1000*days;
        Date dateAfterAdd = new Date();
        dateAfterAdd.setTime(dateTime);
        return dateAfterAdd;
    }
}
