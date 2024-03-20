package com.onedot.win.common.io.jdbc.tablediff;

import lombok.NonNull;
import com.onedot.win.common.io.db.Row;
import com.onedot.win.common.io.sql.SqlResult;
import com.onedot.win.common.lang.exception.lang.IllegalArgumentException;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.common.util.lang.BeanUtil;
import com.onedot.win.common.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultDiff {
    private final List<String> columns = new ArrayList<>();
    private SqlResult leftResult;
    private SqlResult rightResult;

    public static ResultDiff newInstance() {
        return new ResultDiff();
    }

    public ResultDiff result(SqlResult leftResult, SqlResult rightResult) {
        this.leftResult = leftResult;
        this.rightResult = rightResult;

        return this;
    }

    public ResultDiff column(@NonNull String column) {
        if (StringUtil.isBlank(column)) {
            throw new IllegalArgumentException("column can't be blank");
        }

        columns.add(column);
        return this;
    }

    public ResultDiff columns(String... columns) {
        for (String column : columns) {
            column(column);
        }
        return this;
    }

    public ResultDiff columns(List<String> columns) {
        if (CollectionUtil.isEmpty(columns)) {
            return this;
        }

        this.columns.addAll(columns);
        return this;
    }

    public TableDiffResult diff() {
        validInput();

        if (null == leftResult || null == rightResult) {
            return TableDiffResult.oneUnmatched();
        }

        Map<Long, Row> leftMap = leftResult.getTable().getIdMap();
        Map<Long, Row> rightMap = rightResult.getTable().getIdMap();
        if (BeanUtil.hasZero(leftMap.size(), rightMap.size())
                || leftMap.size() != rightMap.size()) {
            int diffNum = BeanUtil.diff(leftMap.size(), rightMap.size());
            return TableDiffResult.someUnmatched(diffNum);
        }

        return diffTable(leftMap, rightMap);
    }

    private TableDiffResult diffTable(Map<Long, Row> leftMap, Map<Long, Row> rightMap) {
        int unmatchedNum = 0;
        for (Map.Entry<Long, Row> entry : leftMap.entrySet()) {
            Long id = entry.getKey();
            Row leftRow = entry.getValue();
            Row rightRow = rightMap.get(id);

            if (rightRow == null) {
                unmatchedNum++;
                continue;
            }

            unmatchedNum += diffRow(leftRow, rightRow);
        }

        if (0 == unmatchedNum) {
            return TableDiffResult.success();
        }

        return TableDiffResult.someUnmatched(unmatchedNum);
    }

    private int diffRow(Row leftRow, Row rightRow) {
        int diffNum = 0;
        for (String column : columns) {
            Object leftValue = leftRow.get(column);
            Object rightValue = rightRow.get(column);

            if (!BeanUtil.equals(leftValue, rightValue)) {
                diffNum++;
            }
        }

        return diffNum;
    }

    private void validInput() {
        if (CollectionUtil.isEmpty(columns)) {
            throw new IllegalArgumentException("DiffStrategy.columns can't be empty");
        }
    }

}
