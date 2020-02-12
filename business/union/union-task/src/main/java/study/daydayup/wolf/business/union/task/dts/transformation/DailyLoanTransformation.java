package study.daydayup.wolf.business.union.task.dts.transformation;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.io.db.Operator;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Statistics;
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
    private Statistics statistics;

    public Statistics transform(@NonNull Table table) {
        if (!CollectionUtil.hasValue(table)) {
            return null;
        }

        statistics = new Statistics();
        statistics.addKeyColumn("org_id", "date");
        initOperator(statistics);


        return statistics;
    }

    private void initOperator(Statistics statistics) {
        Operator operator = Operator.newTask(statistics);
        operator.map()
                .rename("seller_id", "org_id")
                .toLocalDate("created_at", "date")
                .toTag();

        operator.match()
                .equal("trade_type", TradeTypeEnum.LOAN_CONTRACT.getCode());
        operator.aggregate()
                .count("*", "request_count");


        operator = Operator.addJob();
        operator.match()
                .equal("trade_type", TradeTypeEnum.LOAN_CONTRACT.getCode())
                .hasTag(TradeTag.FIRST_TRADE);
        operator.aggregate()
                .count("first_request_count");
    }

    private void transformTable(Table table) {
        for (Row row : table) {
            Operator.execute(row);
        }
    }
}
