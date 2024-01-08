package study.daydayup.wolf.dts.transformer.matcher;

import study.daydayup.wolf.common.io.db.Row;

/**
 * study.daydayup.wolf.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 11:44 上午
 **/
public interface Matcher {
    void init(String column);

    void init(String column, Object value);

    boolean match(Row row);
}
