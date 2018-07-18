package me.cc200.base.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class DateUtil {

    public static final ZoneId CHINA = ZoneId.of("+08:00");

    public static final DateTimeFormatter DTF_COMMON = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime now() {
        return LocalDateTime.now(CHINA);
    }

    public static String nowStr() {
        return now().format(DTF_COMMON);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(CHINA).toInstant());
    }

    public static LocalDateTime toDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), CHINA);
    }


}
