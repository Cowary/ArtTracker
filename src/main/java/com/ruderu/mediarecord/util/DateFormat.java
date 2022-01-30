package com.ruderu.mediarecord.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public enum DateFormat {
    ddMMyyyy("dd.MM.yyyy"),
    ddMMyyyyHHmm("dd.MM.yyyy HH:mm"),
    yyyyMMddHHmmNoDots("yyyy MM dd HH mm"),
    HTML("yyyy-MM-dd'T'HH:mm"),
    HTMLshort("yyyy-MM-dd"),
    CRON("mm HH dd MM '*");

    public static final String HTML_PATTERN = "yyyy-MM-dd'T'HH:mm";
    public static final String HTMLshort_PATTER = "yyyy-MM-dd";

    private final String pattern;
    private final ThreadLocal<SimpleDateFormat> format;


    DateFormat(String pattern) {
        this.pattern = pattern;
        this.format = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
    }

    public Date parse(String str) {
        try {
            return format.get().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(str + " does not conform to " + pattern);
        }
    }

    public String format(Date date) {
        return format.get().format(date);
    }

    public String formatNow() {
        return format.get().format(DateUtil.now());
    }

    public String formatThen(int diff, TimeUnit unit) {
        return format(DateUtil.then(diff, unit));
    }
}


