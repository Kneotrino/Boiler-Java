package com.piggymade.helper;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateUtil {
    public static String convertFormat(String date, String format){
        try{
            final String[] split = date.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.valueOf(split[0]), Integer.valueOf(split[1]) - 1, Integer.valueOf(split[2]));
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern(format);
            final String format1 = sdf.format(calendar.getTime());
            return format1;
        }catch (Exception e){
            e.printStackTrace();
            return date;
        }
    }

    public static String getTodayDate(String format){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
