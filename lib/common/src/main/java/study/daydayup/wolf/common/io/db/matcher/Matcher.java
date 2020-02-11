package study.daydayup.wolf.common.io.db.matcher;

import study.daydayup.wolf.common.io.db.Row;

/**
 * study.daydayup.wolf.common.io.db.matcher
 *
 * @author Wingle
 * @since 2020/2/11 11:44 上午
 **/
public interface Matcher {
    boolean match(Row row);
}
