package com.wolf.common.io.sql.clause;

import com.wolf.common.io.sql.statement.Statement;

/**
 * com.wolf.common.io.sql.clause
 *
 * @author Wingle
 * @since 2021/11/7 上午1:00
 **/
public class EqualClause extends AbstractClause implements Clause {
    @Override
    public Statement build(String column, String clause, Object value) {
        DefaultClause defaultClause = new DefaultClause();
        defaultClause.setPrepared(prepared);
        return defaultClause.build(clause, clause, value);
    }
}
