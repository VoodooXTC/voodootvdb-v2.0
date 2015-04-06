package com.joss.voodootvdb.utils;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 6:29 PM
 */
public class DateFormatter {

    public static String format(String yearMonthDay){
        try {
            SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = formatIn.parse(yearMonthDay);

            SimpleDateFormat formatOut = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
            return formatOut.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return yearMonthDay;
    }

    public static String formatIso(String iso8601String, String defaultValue){
        try{
            Date date = new DateTime(iso8601String).toDate();
            SimpleDateFormat formatOut = new SimpleDateFormat("MMM d, yyyy mm:ss", Locale.getDefault());
            return formatOut.format(date);
        } catch (Exception e){
            return defaultValue;
        }
    }

    public static long toMillis(String time, long defaultValue){
        try{
            return new DateTime(time).getMillis();
        } catch (Exception e){
            return defaultValue;
        }
    }
}
