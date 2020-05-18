package study.daydayup.wolf.business.union.task.dts.transformation;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.transformation.DbTransformation;
import study.daydayup.wolf.dts.transformation.Operator;
import study.daydayup.wolf.dts.transformation.Statistics;
import study.daydayup.wolf.dts.transformation.Transformation;

import java.util.HashMap;
import java.util.Map;

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
                .mapCount("auth_type",getAuthTypeMap());

        return transformation.transform(stream, true);
    }

    private Map<Object, String> getAuthTypeMap() {
        Map<Object, String> map = new HashMap<>(8);
        map.put(1, "basic_info_count");
        map.put(2, "liveness_count");
        map.put(3, "aadhaar_count");
        map.put(4, "pan_card_count");
        map.put(5, "passport_count");
        map.put(6, "driving_license_count");
        map.put(7, "voter_count");
        map.put(8, "bank_card_count");
        map.put(9, "kyc_count");

        map.put(20, "auth_success_count");
        map.put(50, "install_count");
        return map;
    }
}
