package com.example.smartairportsystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatUtil {
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static String getCurrentTime(){
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
