package study.daydayup.wolf.common.io.jdbc.tablediff;

import lombok.NonNull;
import study.daydayup.wolf.common.io.jdbc.JDBC;
import study.daydayup.wolf.common.io.jdbc.tablediff.strategy.LatestStrategy;
import study.daydayup.wolf.common.io.jdbc.tablediff.strategy.TableStrategy;
import study.daydayup.wolf.common.io.sql.SqlResult;
import study.daydayup.wolf.common.lang.exception.lang.IllegalArgumentException;
import study.daydayup.wolf.common.util.collection.CollectionUtil;

public class TableDiffExecutor {
    private final TableDiffResult diffResult;
    private TableDiff diff;

    public TableDiffExecutor() {
        diffResult = new TableDiffResult();
    }

    public TableDiffResult execute(@NonNull TableDiff diff) {
        diff.valid();

        if (!diff.isRoot()) {
            throw new IllegalArgumentException("TableDiff is not the root");
        }

        this.diff = diff;
        executeRootDiff();
        executeChildrenDiff();
        closeConnection();

        return diffResult;
    }

    private void closeConnection() {
        JDBC.closeAll();
    }

    private void executeRootDiff() {
        executeDiff(diff);
    }

    private void executeChildrenDiff() {
        if (CollectionUtil.isEmpty(diff.getChildren())) {
            return;
        }

        for (TableDiff tableDiff : diff.getChildren()) {
            executeDiff(tableDiff);
        }
    }

    private void executeDiff(TableDiff tableDiff) {
        sameNameTablesDiff(tableDiff);
        differentNameTablesDiff(tableDiff);
    }

    private void sameNameTablesDiff(TableDiff tableDiff) {
        if (CollectionUtil.isEmpty(tableDiff.getSameNameTables())) {
            return;
        }

        for (String tableName : tableDiff.getSameNameTables()) {
            String sql = getSql(tableName);

            SqlResult leftResult = tableDiff.getLeftConn().execute(sql);
            SqlResult rightResult = tableDiff.getRightConn().execute(sql);

            TableDiffResult result = diffSqlResult(tableName, tableDiff, leftResult, rightResult);
            diffResult.merge(result);
        }
    }


    private void differentNameTablesDiff(TableDiff tableDiff) {
        if (CollectionUtil.isEmpty(tableDiff.getLeftTables())) {
            return;
        }

        for (int i = 0; i < tableDiff.getLeftTables().size(); i++) {
            String leftTable = tableDiff.getLeftTables().get(i);
            String rightTable = tableDiff.getRightTables().get(i);

            String leftSql = getSql(leftTable);
            String rightSql = getSql(rightTable);

            SqlResult leftResult = tableDiff.getLeftConn().execute(leftSql);
            SqlResult rightResult = tableDiff.getRightConn().execute(rightSql);

            TableDiffResult result = diffSqlResult(leftTable, tableDiff, leftResult, rightResult);
            diffResult.merge(result);
        }
    }

    private TableDiffResult diffSqlResult(String tableName, TableDiff tableDiff, SqlResult leftResult, SqlResult rightResult) {
        TableDiffResult result = ResultDiff.newInstance()
                .result(leftResult, rightResult)
                .columns(tableDiff.getColumns())
                .diff();

        if (result.isSuccess()) {
            result.addMatchedTable(tableName);
        } else {
            result.addUnmatchedTable(tableName);
        }

        return result;
    }

    private String getSql(String tableName) {
        TableStrategy strategy = new LatestStrategy();
        return strategy.getSql(tableName);
    }

}
