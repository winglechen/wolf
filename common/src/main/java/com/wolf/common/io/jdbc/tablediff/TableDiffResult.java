package com.wolf.common.io.jdbc.tablediff;

import lombok.Data;
import lombok.NonNull;
import com.wolf.common.lang.ds.ListMap;
import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.common.util.collection.MapUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class TableDiffResult implements Serializable {
    private boolean success;

    private int matchedNum = 0;
    private int unmatchedNum = 0;

    private List<String> matchedTableList = new ArrayList<>();
    private ListMap<String, Object> unmatchedTableList = new ListMap<>();

    public static TableDiffResult oneUnmatched() {
        TableDiffResult result = new TableDiffResult();
        result.setSuccess(false);
        result.setUnmatchedNum(1);

        return result;
    }

    public static TableDiffResult success() {
        TableDiffResult result = new TableDiffResult();
        result.setSuccess(true);
        result.setMatchedNum(1);

        return result;
    }

    public static TableDiffResult someUnmatched(int num) {
        TableDiffResult result = new TableDiffResult();
        result.setSuccess(false);
        result.setUnmatchedNum(num);

        return result;
    }

    public void addMatchedTable(@NonNull String table) {
        matchedTableList.add(table);
    }

    public void addUnmatchedTable(@NonNull String table) {
        addUnmatchedTable(table, null);
    }

    public void addUnmatchedTable(@NonNull String table, Object msg) {
        unmatchedTableList.add(table, msg);
    }

    public void merge(TableDiffResult result) {
        if (!result.isSuccess()) {
            success = false;
        }

        matchedNum += result.getMatchedNum();
        unmatchedNum += result.getUnmatchedNum();

        mergeMatchedTable(result);
        mergeUnmatchedTable(result);
    }

    private void mergeMatchedTable(TableDiffResult result) {
        if (CollectionUtil.isEmpty(result.getMatchedTableList())) {
            return;
        }

        matchedTableList.addAll(result.getMatchedTableList());
    }

    private void mergeUnmatchedTable(TableDiffResult result) {
        if (MapUtil.isEmpty(result.getUnmatchedTableList())) {
            return;
        }

        for (Map.Entry<String, List<Object>> entry : result.getUnmatchedTableList().entrySet()) {
            unmatchedTableList.addAll(entry.getKey(), entry.getValue());
        }
    }
}
