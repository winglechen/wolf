package com.onedot.win.common.io.sql.clause;

import com.onedot.win.common.io.sql.builder.SqlKeyword;
import com.onedot.win.common.io.sql.statement.SqlStatement;
import com.onedot.win.common.io.sql.statement.Statement;
import com.onedot.win.common.io.sql.util.PlaceHolder;
import com.onedot.win.common.io.sql.util.ValueFormatter;
import com.onedot.win.common.lang.exception.lang.IllegalArgumentException;
import com.onedot.win.common.util.lang.StringUtil;

import java.util.Collection;

/**
 * com.onedot.win.common.io.sql.clause
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
