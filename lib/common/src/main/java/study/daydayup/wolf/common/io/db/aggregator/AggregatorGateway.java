package study.daydayup.wolf.common.io.db.aggregator;


import study.daydayup.wolf.common.io.db.Row;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/4 5:19 下午
 **/
public class AggregatorGateway implements Aggregator {
    public AggregatorGateway() {
    }

    @Override
    public void aggregate(Row row) {

    }

    public int count() {
        return 0;
    }

    public Long minId() {
        return 0L;
    }

    public Long maxId() {
        return 0L;
    }

    public Object min(String column) {
        return null;
    }

    public Object max(String column) {
        return null;
    }

    public Object sum(String column) {
        return BigDecimal.ZERO;
    }

    public Object avg(String column) {
        return 0;
    }

}
