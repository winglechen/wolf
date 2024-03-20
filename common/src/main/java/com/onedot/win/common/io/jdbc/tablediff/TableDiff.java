package com.onedot.win.common.io.jdbc.tablediff;


import lombok.Getter;
import lombok.NonNull;
import com.onedot.win.common.io.jdbc.JDBC;
import com.onedot.win.common.lang.exception.lang.IllegalArgumentException;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.common.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class TableDiff {
    private final TableDiff root;
    private final List<TableDiff> children;

    private final List<String> sameNameTables;
    private final List<String> leftTables;
    private final List<String> rightTables;
    private final List<String> columns;

    private JDBC leftConn = null;
    private JDBC rightConn = null;


    public static TableDiff newInstance() {
        return new TableDiff();
    }

    public TableDiff() {
        this(null);
    }

    public TableDiff(TableDiff root) {
        this.root = root;
        children = new ArrayList<>();
        sameNameTables = new ArrayList<>();
        leftTables = new ArrayList<>();
        rightTables = new ArrayList<>();
        columns = new ArrayList<>();
    }

    public TableDiff column(@NonNull String column) {
        if (StringUtil.isBlank(column)) {
            throw new IllegalArgumentException("column can't be blank");
        }

        columns.add(column);
        return this;
    }

    public TableDiff columns(String... columns) {
        for (String column : columns) {
            column(column);
        }
        return this;
    }

    public TableDiff leftDb(String url, String username, String password) {
        leftConn = JDBC.connect(url, username, password);
        return this;
    }

    public TableDiff rightDb(String url, String username, String password) {
        rightConn = JDBC.connect(url, username, password);
        return this;
    }

    public TableDiff sameNameTable(String... tables) {
        sameNameTables.addAll(Arrays.asList(tables));
        return this;
    }

    public TableDiff differentNameTable(@NonNull String leftTable, @NonNull String rightTable) {
        leftTables.add(leftTable);
        rightTables.add(rightTable);
        return this;
    }

    public TableDiff nextJob() {
        TableDiff parent = root == null ? this : root;
        TableDiff child = new TableDiff(parent);

        if (root == null) {
            children.add(child);
        } else {
            root.addChild(child);
        }

        return child;
    }

    public TableDiffResult diff() {
        TableDiffExecutor executor = new TableDiffExecutor();

        TableDiff parent = root == null ? this : root;
        return executor.execute(parent);
    }

    public void addChild(TableDiff child) {
        children.add(child);
    }

    public boolean isRoot() {
        return root == null;
    }

    public void valid() {
        if (null == leftConn || null == rightConn) {
            throw new IllegalArgumentException("TableDiff.leftConn and rightConn can't be null");
        }

        if (CollectionUtil.isEmpty(columns)) {
            throw new IllegalArgumentException("TableDiff.columns can't be empty");
        }

        if (CollectionUtil.isEmpty(sameNameTables) && CollectionUtil.isEmpty(leftTables)) {
            throw new IllegalArgumentException("TableDiff.table can't be empty");
        }

        if (leftTables.size() != rightTables.size()) {
            throw new IllegalArgumentException("differentNameTable size doesn't match");
        }
    }
}
