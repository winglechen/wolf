package study.daydayup.wolf.framework.middleware.hbase;

import lombok.Data;
import study.daydayup.wolf.framework.middleware.hbase.executor.Executor;
import study.daydayup.wolf.framework.middleware.hbase.executor.hbase.annotation.HBaseTableName;
import study.daydayup.wolf.framework.middleware.hbase.option.OptionCriteria;
import study.daydayup.wolf.framework.middleware.hbase.result.HBaseResult;

/**
 * QueryBuilder
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/26 16:06
 **/
@Data
public class OptionBuilder {
    private OptionCriteria optionCriteria;

    private Class table;

    private Executor executor;

    public OptionBuilder option(OptionCriteria optionCriteria) {
        this.optionCriteria = optionCriteria;
        return this;
    }

    public OptionBuilder table(Class table) {
        HBaseTableName tableName = (HBaseTableName) table.getDeclaredAnnotation(HBaseTableName.class);
        String source = tableName.source();
        optionCriteria.setSource(source);
        optionCriteria.setTable(tableName.name());
        this.table=table;
        return this;
    }

    public HBaseResult execute() {
        return executor.execute(this);
    }
}
