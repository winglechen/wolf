package com.wolf.dts.transformer;

import lombok.Getter;
import com.wolf.common.io.db.Row;
import com.wolf.common.io.db.Table;
import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.dts.transformer.matcher.MatcherGateway;

/**
 * com.wolf.dts.transformation
 *
 * @author Wingle
 * @since 2021/11/22 下午4:22
 **/
public class TableFilter {
    private final Table table;
    @Getter
    private final MatcherGateway matcher;

    public TableFilter(Table table) {
        this.table = table;
        matcher = new MatcherGateway();
    }

    public MatcherGateway match() {
        return matcher;
    }

    public Table filter() {
        Table result = new Table();
        if (CollectionUtil.isEmpty(table)) {
            return result;
        }

        for (Row row : table) {
            if (matcher.match(row)) {
                result.addRow(row);
            }
        }

        return result;
    }
}
