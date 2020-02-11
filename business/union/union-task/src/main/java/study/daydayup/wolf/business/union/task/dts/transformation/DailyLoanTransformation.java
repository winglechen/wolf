package study.daydayup.wolf.business.union.task.dts.transformation;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.io.db.Operator;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.db.mapper.MapperGateway;
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
    private Statistics statistics;

    public Statistics transform(@NonNull String taskName, @NonNull Table table) {
        if (!CollectionUtil.hasValue(table)) {
            return null;
        }

        statistics = new Statistics();
        statistics.addKeyColumn("org_id", "date");

        Operator countOperator = new Operator(statistics);
        countOperator.map()
                .rename("seller_id", "org_id")
                .toLocalDate("created_at", "date")
                .toTag();

        countOperator.match()
                .equal("trade_type", TradeTypeEnum.LOAN_CONTRACT.getCode());
        countOperator.aggregate();


        return statistics;
    }






}
