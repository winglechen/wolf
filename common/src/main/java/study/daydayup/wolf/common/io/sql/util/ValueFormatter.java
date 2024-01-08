package study.daydayup.wolf.common.io.sql.util;

import study.daydayup.wolf.common.io.sql.builder.SqlKeyword;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.common.util.time.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

/**
 * study.daydayup.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 4:58 下午
 **/
public class ValueFormatter {
    public static Object format(Object value) {
        if (value instanceof Number) {
            return value;
        }

        if (value instanceof Date) {
            return StringUtil.join("'", DateUtil.asString((Date) value), "'");
        }

        if (value instanceof LocalDate) {
            return StringUtil.join("'", DateUtil.asString((LocalDate) value), "'");
        }

        if (value instanceof LocalDateTime) {
            return StringUtil.join("'", DateUtil.asString((LocalDateTime) value), "'");
        }

        return StringUtil.join("'", value, "'");
    }

    public static <T> String in(Collection<T> values) {
        if (CollectionUtil.isEmpty(values)) {
            return "()";
        }

        return StringUtil.join(
                SqlKeyword.LEFT_BRACKET,
                join(values),
                SqlKeyword.RIGHT_BRACKET
        );
    }

    public static <T> String join(Collection<T> values) {
        if (CollectionUtil.isEmpty(values)) {
            return "";
        }

        Object[] valueArray = values.toArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            sb.append(format(valueArray[i]));

            if (i < values.size() - 1) {
                sb.append(SqlKeyword.COMMA);
            }
        }

        return sb.toString();
    }
}
