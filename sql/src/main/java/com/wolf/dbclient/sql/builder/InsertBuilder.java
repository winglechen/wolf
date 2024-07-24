package com.wolf.dbclient.sql.builder;

import com.wolf.dbclient.sql.statement.Statement;
import lombok.Getter;
import lombok.NonNull;
import com.wolf.dbclient.sql.util.ValueFormatter;
import com.wolf.common.util.collection.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 4:33 下午
 **/
public class InsertBuilder {
    private final Map<String, Object> data;
    private final boolean prepared;
    private final boolean isFirstValue;

    @Getter
    private String insertColumns;
    @Getter
    private String insertValues;
    @Getter
    private final List<Object> preparedDataList;


    public static InsertBuilder build(@NonNull Map<String, Object> data, boolean prepared, boolean isFirstValue) {
        InsertBuilder builder = new InsertBuilder(data, prepared, isFirstValue);
        builder.parse();

        return builder;
    }

    public InsertBuilder(@NonNull Map<String, Object> data, boolean prepared, boolean isFirstValue) {
        this.data = data;
        this.prepared = prepared;
        this.isFirstValue = isFirstValue;

        this.insertColumns = "";
        this.insertValues = "";
        this.preparedDataList = new ArrayList<>();
    }

    public void parse() {
        if (MapUtil.isEmpty(data)) {
            return;
        }

        if (isFirstValue) {
            parseInsertColumns();
        } else {
            insertColumns = SqlKeyword.COMMA;
        }

        if (prepared) {
            parsePreparedInsertValues();
        } else {
            parseInsertValues();
        }
    }

    private void parseInsertColumns() {
        StringBuilder sql = new StringBuilder();
        String columns = String.join("`, `", data.keySet());

        sql.append(SqlKeyword.LEFT_BRACKET)
                .append("`")
                .append(columns)
                .append("`")
                .append(SqlKeyword.RIGHT_BRACKET)
                .append(SqlKeyword.VALUES);

        insertColumns =  sql.toString();
    }

    private void parseInsertValues() {
        StringBuilder sql = new StringBuilder();
        sql.append(SqlKeyword.LEFT_BRACKET);

        Object[] values = data.values().toArray();
        Object v;
        for (int i = 0, len=values.length; i < len; i++) {
            if (0 != i) {
                sql.append(SqlKeyword.COMMA);
            }
            v = values[i];
            if (v instanceof Statement) {
                v = ((Statement) v).getValue();
            }
            sql.append(ValueFormatter.format(v));
        }

        sql.append(SqlKeyword.RIGHT_BRACKET);

        insertValues = sql.toString();
    }

    private void parsePreparedInsertValues() {
        StringBuilder sql = new StringBuilder();
        sql.append(SqlKeyword.LEFT_BRACKET);

        Object[] values = data.values().toArray();
        Object v;
        for (int i = 0, len=data.size(); i < len; i++) {
            if (0 != i) {
                sql.append(SqlKeyword.COMMA);
            }
            sql.append(SqlKeyword.QUESTION_MARK);

            v = values[i];
            if (prepared) {
                if (v instanceof Statement) {
                    preparedDataList.add(((Statement) v).getValue());
                } else {
                    preparedDataList.add(v);
                }
            }
        }
        sql.append(SqlKeyword.RIGHT_BRACKET);

        insertValues = sql.toString();
    }

}
