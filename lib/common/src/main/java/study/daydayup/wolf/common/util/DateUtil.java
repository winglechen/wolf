package study.daydayup.wolf.common.util;

import java.time.*;
import java.util.Date;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/10/23 10:44 下午
 **/
public class DateUtil {
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date secondsLater(int seconds) {
        LocalDateTime now = LocalDateTime.now();
        return DateUtil.asDate(now.plusSeconds(seconds));
    }

}
