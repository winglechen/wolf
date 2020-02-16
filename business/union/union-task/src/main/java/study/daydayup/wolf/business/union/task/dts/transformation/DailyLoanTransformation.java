package study.daydayup.wolf.business.union.task.dts.transformation;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.io.db.Operator;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.dts.transeformation.Transformation;

/**
 * study.daydayup.wolf.business.union.task.dts.transformation
 *
 * @author Wingle
 * @since 2020/2/8 8:37 下午
 **/
@Component
public class DailyLoanTransformation implements Transformation {

    public Statistics transform(@NonNull Table table) {
        if (!CollectionUtil.hasValue(table)) {
            return null;
        }

        Statistics statistics = new Statistics();
        statistics.setKeyColumns("org_id", "date");

        initOperator(statistics);
        Operator.execute(table);

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
}
