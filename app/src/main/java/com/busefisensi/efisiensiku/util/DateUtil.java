package com.busefisensi.efisiensiku.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateToStringDefault(Date date) {
        return (new SimpleDateFormat("dd MMM yyyy")).format(date);
    }

}
