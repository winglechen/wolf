package com.wolf.common.io.sql.builder;

import org.junit.Test;
import com.wolf.common.io.sql.util.ValueFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/23 11:54 上午
 **/
public class ValueFormatterTest {

    @Test
    public void format() {
        assertEquals("ValueBuilder.format fail", 123, ValueFormatter.format(123));
        assertEquals("ValueBuilder.format fail", "'test'", ValueFormatter.format("test"));
        assertEquals("ValueBuilder.format fail", "'2020-09-01'", ValueFormatter.format(LocalDate.of(2020, 9, 1)));
        assertEquals("ValueBuilder.format fail", "'2020-09-01 01:01:01'", ValueFormatter.format(LocalDateTime.of(2020, 9, 1, 1, 1, 1)));
    }
}