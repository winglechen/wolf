package study.daydayup.wolf.common.io.sql.clause;

import study.daydayup.wolf.common.io.sql.statement.Statement;

/**
 * study.daydayup.wolf.common.io.sql.clause
 *
 * @author Wingle
 * @since 2021/11/7 下午10:33
 **/
public class ClauseGateway extends AbstractClause implements Clause {
    @Override
    public Statement build(String column, String clause, Object value) {
        ClauseLocator locator = new ClauseLocator();
        Clause executor = locator.locate(clause);
        executor.setPrepared(this.prepared);
        return executor.build(column, clause, value);
    }
}
