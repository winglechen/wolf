package com.wolf.dbclient.sql.builder;

import com.wolf.dbclient.sql.statement.Statement;
import lombok.Getter;
import lombok.NonNull;
import com.wolf.dbclient.sql.util.ValueFormatter;
import com.wolf.common.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 4:34 下午
 **/
public class UpdateBuilder {
    private final Map<String, Object> data;
    private final boolean prepared;
    private final boolean onDuplicateKey;
    private boolean isFirst;

    @Getter
    private String updateValues;
    @Getter
    private final List<Object> preparedDataList;


    public static UpdateBuilder build(@NonNull Map<String, Object> data, boolean isFirst, boolean prepared, boolean onDuplicateKey) {
        UpdateBuilder builder = new UpdateBuilder(data, isFirst, prepared, onDuplicateKey);
        builder.parse();

        return builder;
    }

    public UpdateBuilder(@NonNull Map<String, Object> data, boolean isFirst, boolean prepared, boolean onDuplicateKey) {
        this.prepared = prepared;
        this.data = data;
        this.onDuplicateKey = onDuplicateKey;
        this.isFirst = isFirst;

        this.updateValues = "";
        this.preparedDataList = new ArrayList<>();
    }

    public void parse() {
        if (data.isEmpty()) {
            return ;
        }

        StringBuilder sql = new StringBuilder();

        for (Map.Entry<String, Object> entry: data.entrySet()) {
            addUpdatePrefix(sql);

            setKey(sql, entry.getKey());
            setValue(sql, entry.getValue());
        }

        updateValues = sql.toString();
    }

    private void addUpdatePrefix(StringBuilder sql) {
        if (!isFirst) {
            sql.append(SqlKeyword.COMMA);
        } else {
            if (onDuplicateKey) {
                sql.append(SqlKeyword.DUPLICATE_UPDATE);
            } else {
                sql.append(SqlKeyword.SET);
            }
            isFirst = false;
        }
    }

    private void setKey(StringBuilder sql, String key) {
        sql.append(StringUtil.quote(key))
                .append(SqlKeyword.BLANK)
                .append(SqlKeyword.EQUAL)
                .append(SqlKeyword.BLANK);
    }

    private void setValue(StringBuilder sql, Object value) {
        if (value instanceof Statement) {
            setValueStatement(sql, (Statement)value);
        } else if (prepared) {
            this.preparedDataList.add(value);
            sql.append(SqlKeyword.QUESTION_MARK);
        } else {
            sql.append(ValueFormatter.format(value));
        }
    }

    private void setValueStatement(StringBuilder sql, Statement s) {
        sql.append(s.getSql());
        if (prepared && null != s.getValue()) {
            preparedDataList.add(s.getValue());
        }
    }

}
