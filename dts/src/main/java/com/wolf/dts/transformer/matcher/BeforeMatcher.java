package com.wolf.dts.transformer.matcher;

import com.wolf.common.io.db.Row;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * com.wolf.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:58 下午
 **/
public class BeforeMatcher extends AbstractMatcher implements Matcher {
    @Override
    public boolean match(Row row) {
        Object col = row.get(column);
        if (col instanceof Timestamp && value instanceof LocalDateTime) {
            return ((Timestamp) col).toLocalDateTime().compareTo((LocalDateTime) value) < 0;
        }

        if (col instanceof Date && value instanceof LocalDate) {
            return ((Date) col).toLocalDate().compareTo((LocalDate) value) < 0;
        }

        if (col instanceof Time && value instanceof LocalTime) {
            return ((Time) col).toLocalTime().compareTo((LocalTime) value) < 0;
        }

        if (col instanceof LocalDateTime && value instanceof LocalDateTime) {
            return ((LocalDateTime) col).compareTo((LocalDateTime) value) < 0;
        }

        if (col instanceof LocalDate && value instanceof LocalDate) {
            return ((LocalDate) col).compareTo((LocalDate) value) < 0;
        }

        if (col instanceof LocalTime && value instanceof LocalTime) {
            return ((LocalTime) col).compareTo((LocalTime) value) < 0;
        }

        return false;
    }
}
