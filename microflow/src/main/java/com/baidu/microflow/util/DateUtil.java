package com.baidu.microflow.util;

import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangcuibao on 2016/11/7.
 */
public class DateUtil {
    /**
     * 当前日期
     */
    public static String getCurrentDate() {
        Date date = new Date();
        return formatDate(date);
    }

    /**
     * 格式化日期
     */
    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatDate = simpleDateFormat.format(date);
        return formatDate;
    }
}
