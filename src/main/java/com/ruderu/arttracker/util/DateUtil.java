package com.ruderu.arttracker.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static Date now() {
        return new Date();
    }

    public static Date then(long difference, TimeUnit unit) {
        return new Date(now().getTime() + unit.toMillis(difference));
    }

    public static long difference(Date from, Date to, TimeUnit unit) {
        return unit.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS);
    }

    public static Date def() {
        return new Date(-5364673200000L);
    }

    public static int getYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
}
