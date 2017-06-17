package com.venkyapps.airquality.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class MyDateUtils {

    public static SimpleDateFormat sdfIso8601Format = new SimpleDateFormat(MyConstants.DATE_ISO_8601_FORMAT, Locale.ENGLISH);
    public static SimpleDateFormat sdfNewFormat = new SimpleDateFormat(MyConstants.MY_DATE_TIME_FORMAT, Locale.ENGLISH);


    public static String getBrezometerAqiTime(String dateIso8601) {
        sdfIso8601Format.setTimeZone(TimeZone.getTimeZone("GMT"));
        //sdfNewFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        try {
            Date formatedDate = sdfIso8601Format.parse(dateIso8601);
            return "Updated on " + sdfNewFormat.format(formatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Updated on " + dateIso8601;
        } catch (NullPointerException e) {
            return "";
        }
    }
}
