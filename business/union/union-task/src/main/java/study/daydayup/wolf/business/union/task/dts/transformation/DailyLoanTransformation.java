package study.daydayup.wolf.business.union.task.dts.transformation;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.db.Mapper;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.dts.transeformer.Transformation;

/**
 * study.daydayup.wolf.business.union.task.dts.transformation
 *
 * @author Wingle
 * @since 2020/2/8 8:37 下午
 **/
@Component
public class DailyLoanTransformation implements Transformation {

    public Statistics transform(@NonNull String taskName, @NonNull Table table) {
        if (!CollectionUtil.hasValue(table)) {
            return null;
        }

        map(table);

        return null;
    }

    public void map(Table table) {
        for (Row row: table) {
            Mapper mapper = new Mapper(row)
                    .toLocalDate("created_at", "created_date")
                    .toTag();
        }
    }

    public Table filter(Table table) {
        return table;
    }




}
