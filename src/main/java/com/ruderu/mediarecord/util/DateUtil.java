package com.ruderu.mediarecord.util;

import java.util.Date;
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
}