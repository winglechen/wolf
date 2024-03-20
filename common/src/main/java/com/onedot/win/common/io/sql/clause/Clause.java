package com.onedot.win.common.io.sql.clause;

import com.onedot.win.common.io.sql.statement.Statement;

/**
 * com.onedot.win.common.io.sql.clause
 *
 * @author Wingle
 * @since 2021/11/7 上午1:00
 **/
public interface Clause {
    void setPrepared(boolean prepared);

    Statement build(String column, String clause, Object value);
}
