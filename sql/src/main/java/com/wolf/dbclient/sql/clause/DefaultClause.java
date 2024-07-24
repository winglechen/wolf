package com.wolf.dbclient.sql.clause;

import com.wolf.dbclient.sql.builder.SqlKeyword;
import com.wolf.dbclient.sql.statement.SqlStatement;
import com.wolf.dbclient.sql.statement.Statement;
import com.wolf.dbclient.sql.util.ValueFormatter;
import com.wolf.common.util.lang.StringUtil;

/**
 * com.wolf.common.io.sql.clause
 *
 * @author Wingle
 * @since 2021/11/7 上午1:00
 **/
public class DefaultClause extends AbstractClause implements Clause {
    @Override
    public Statement build(String column, String clause, Object value) {
        Object palaceHolder = prepared ? SqlKeyword.QUESTION_MARK : ValueFormatter.format(value);
        if (!prepared) {
            value = null;
        }

        String sql = StringUtil.join(
                StringUtil.quote(column),
                SqlKeyword.BLANK, clause.toUpperCase(),
                SqlKeyword.BLANK, palaceHolder
        );

        return SqlStatement.of(sql, value);
    }
}
