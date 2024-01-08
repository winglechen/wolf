package study.daydayup.wolf.common.io.sql.statement;

import java.util.List;

/**
 * study.daydayup.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/2/6 11:12 下午
 **/
public interface Statement {
    String getSql();
    Object getValue();
    List<Object> getValues();
}
