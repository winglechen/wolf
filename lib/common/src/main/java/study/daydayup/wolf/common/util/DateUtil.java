package study.daydayup.wolf.common.util;

import lombok.NonNull;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/10/23 10:44 下午
 **/
public class DateUtil {
    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);

    public static Date asDate(@NonNull LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(@NonNull LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(@NonNull Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate asLocalDate(@NonNull String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
        return LocalDate.parse(str, formatter);
    }

    public static LocalDateTime asLocalDateTime(@NonNull String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
        return LocalDateTime.parse(str, formatter);
    }

    public static LocalDateTime asLocalDateTime(@NonNull Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date secondsLater(int seconds) {
        LocalDateTime now = LocalDateTime.now();
        return DateUtil.asDate(now.plusSeconds(seconds));
    }

    public static String asString(@NonNull Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        return sdf.format(date);
    }

    public static String asString(@NonNull LocalDate localDate) {
        return localDate.format(formatter);
    }

    public static String asString(@NonNull LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

}
