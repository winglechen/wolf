package study.daydayup.wolf.business.union.task.dts.transformation;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.transformation.DbTransformation;
import study.daydayup.wolf.dts.transformation.Operator;
import study.daydayup.wolf.dts.transformation.Statistics;
import study.daydayup.wolf.dts.transformation.Transformation;

/**
 * study.daydayup.wolf.business.union.task.dts.transformation
 *
 * @author Wingle
 * @since 2020/5/5 11:14 上午
 **/
@Component
public class UserCreditLogTransformation implements Transformation {
    public Statistics latest(Table stream, MysqlSink mysqlSink) {
        if (CollectionUtil.isEmpty(stream)) {
            return null;
        }

        DbTransformation transformation = DbTransformation.newTask(mysqlSink);
        Operator operator;

        operator = transformation.addJob();
        operator.map()
                .toLocalDate("created_at", "date");

        operator.aggregate()
                .count("register_count");

        return transformation.transform(stream, true);
    }
}
