package com.wolf.dbclient.sql.clause;

import com.wolf.dbclient.sql.builder.SqlKeyword;
import com.wolf.dbclient.sql.statement.SqlStatement;
import com.wolf.dbclient.sql.statement.Statement;
import com.wolf.dbclient.sql.util.PlaceHolder;
import com.wolf.dbclient.sql.util.ValueFormatter;
import com.wolf.common.lang.exception.lang.IllegalArgumentException;
import com.wolf.common.util.lang.StringUtil;

import java.util.Collection;

/**
 * com.wolf.common.io.sql.clause
 *
 * @author Wingle
 * @since 2021/11/7 上午1:00
 **/
public class InClause extends AbstractClause implements Clause {
    @Override
    public Statement build(String column, String clause, Object value) {
        if (value == null) {
            DefaultClause defaultClause = new DefaultClause();
            return defaultClause.build(clause, clause, value);
        }

        if (!(value instanceof Collection)) {
            throw new IllegalArgumentException("invalid in args");
        }

        String placeHolder = prepared ? PlaceHolder.in((Collection<?>) value) : ValueFormatter.in((Collection<?>) value);
        String sql = StringUtil.join(
                StringUtil.quote(column),
                SqlKeyword.BLANK, clause.toUpperCase(),
                SqlKeyword.BLANK, placeHolder
        );

        SqlStatement statement = new SqlStatement(sql);
        if (!prepared) {
            return statement;
        }

        for (Object o : (Collection<?>) value) {
            statement.addValue(o);
        }

        return statement;
    }
}
