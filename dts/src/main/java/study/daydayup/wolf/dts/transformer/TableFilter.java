package study.daydayup.wolf.dts.transformer;

import lombok.Getter;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.dts.transformer.matcher.MatcherGateway;

/**
 * study.daydayup.wolf.dts.transformation
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
