package study.daydayup.wolf.common.util.time;

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
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);

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
       return asString(date, DEFAULT_DATETIME_FORMAT);
    }

    public static String asString(@NonNull Date date, @NonNull String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String asString(@NonNull LocalDate localDate) {
        return asString(localDate, null);
    }

    public static String asString(@NonNull LocalDate localDate, String format) {
        if (null == format) {
            return localDate.format(DEFAULT_FORMATTER);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    public static String asString(@NonNull LocalDateTime localDateTime) {
        return asString(localDateTime, null);
    }

    public static String asString(@NonNull LocalDateTime localDateTime, String format) {
        if (format == null) {
            return localDateTime.format(DEFAULT_FORMATTER);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    public static boolean isSameDay(@NonNull LocalDateTime time) {
        return isSameDay(time, LocalDateTime.now());
    }

    public static boolean isSameDay(@NonNull LocalDateTime time, @NonNull LocalDateTime vsTime) {
        if (time.getYear() != vsTime.getYear()) {
            return false;
        }

        if (time.getMonth() != vsTime.getMonth()) {
            return false;
        }

        return time.getDayOfMonth() == vsTime.getDayOfMonth();
    }
}
