package study.daydayup.wolf.common.io.sql.util;

import lombok.NonNull;
import study.daydayup.wolf.common.io.sql.builder.SqlKeyword;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.common.io.sql.clause
 *
 * @author Wingle
 * @since 2021/11/7 上午1:09
 **/
public class PlaceHolder {
    public static String create(int num) {
        if (num <= 0) {
            return SqlKeyword.BLANK;
        }

        if (num == 1) {
            return format(SqlKeyword.QUESTION_MARK) ;
        }

        List<String> rowList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            rowList.add(SqlKeyword.QUESTION_MARK);
        }

        return format(StringUtil.joinWith(SqlKeyword.COMMA, rowList));
    }

    public static String create(Object... args) {
        return create(args.length);
    }

    public static String create(@NonNull Collection<?> args) {
        return create(args.size());
    }

    public static String create(@NonNull Map<String, Object> ps) {
        return create(ps.size());
    }

    public static String in(@NonNull Collection<?> args) {
        if (CollectionUtil.isEmpty(args)) {
            return "()";
        }

        return StringUtil.join(
                SqlKeyword.LEFT_BRACKET,
                create(args),
                SqlKeyword.RIGHT_BRACKET
        );
    }

    public static String batchInsert(@NonNull Collection<Map<String, Object>> rows) {
        if (CollectionUtil.isEmpty(rows)) {
            return SqlKeyword.BLANK;
        }

        List<String> rowList = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            rowList.add(StringUtil.join(
                    SqlKeyword.LEFT_BRACKET,
                    create(row),
                    SqlKeyword.RIGHT_BRACKET
            ));
        }

        return StringUtil.join(SqlKeyword.COMMA, rowList);
    }

    private static String format(String s) {
        return StringUtil.join(
                SqlKeyword.BLANK,
                s,
                SqlKeyword.BLANK
        );
    }
}
