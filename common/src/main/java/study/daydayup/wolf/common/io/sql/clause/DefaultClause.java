package study.daydayup.wolf.common.io.sql.clause;

import study.daydayup.wolf.common.io.sql.builder.SqlKeyword;
import study.daydayup.wolf.common.io.sql.statement.SqlStatement;
import study.daydayup.wolf.common.io.sql.statement.Statement;
import study.daydayup.wolf.common.io.sql.util.ValueFormatter;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.common.io.sql.clause
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
