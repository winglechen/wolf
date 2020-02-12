package study.daydayup.wolf.common.io.db.aggregator;


import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.db.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/4 5:19 下午
 **/
public class AggregatorGateway extends AbstractAggregator implements Aggregator {
    private List<Aggregator> aggregatorList;

    public AggregatorGateway(Statistics statistics) {
        this.statistics = statistics;
        this.aggregatorList = new ArrayList<>(5);
    }

    @Override
    public void aggregate(Row row) {
        if (aggregatorList.isEmpty()) {
            return;
        }

        for (Aggregator aggregator : aggregatorList) {
            aggregator.aggregate(row);
        }
    }

    public AggregatorGateway minId(@NonNull String statisticsColumn) {
        Aggregator aggregator = new MinAggregator();

        aggregator.init(statistics, Table.DEFAULT_ID_COLUMN, statisticsColumn);
        aggregatorList.add(aggregator);

        return this;
    }

    public AggregatorGateway maxId(@NonNull String statisticsColumn) {
        Aggregator aggregator = new MaxAggregator();

        aggregator.init(statistics, Table.DEFAULT_ID_COLUMN, statisticsColumn);
        aggregatorList.add(aggregator);

        return this;
    }

    public AggregatorGateway count(@NonNull String statisticsColumn) {
        return count(Table.DEFAULT_COUNT_COLUMN, statisticsColumn);
    }

    public AggregatorGateway count(@NonNull String rowColumn, @NonNull String statisticsColumn) {
        Aggregator aggregator = new CountAggregator();

        aggregator.init(statistics, rowColumn, statisticsColumn);
        aggregatorList.add(aggregator);

        return this;
    }

    public AggregatorGateway DistinctCount(@NonNull String rowColumn, @NonNull String statisticsColumn) {
        Aggregator aggregator = new DistinctCountAggregator();

        aggregator.init(statistics, rowColumn, statisticsColumn);
        aggregatorList.add(aggregator);

        return this;
    }


    public AggregatorGateway min(@NonNull String rowColumn, @NonNull String statisticsColumn) {
        Aggregator aggregator = new MinAggregator();

        aggregator.init(statistics, rowColumn, statisticsColumn);
        aggregatorList.add(aggregator);

        return this;
    }

    public AggregatorGateway max(@NonNull String rowColumn, @NonNull String statisticsColumn) {
        Aggregator aggregator = new MaxAggregator();

        aggregator.init(statistics, rowColumn, statisticsColumn);
        aggregatorList.add(aggregator);

        return this;
    }

    public AggregatorGateway sum(@NonNull String rowColumn, @NonNull String statisticsColumn) {
        Aggregator aggregator = new SumAggregator();

        aggregator.init(statistics, rowColumn, statisticsColumn);
        aggregatorList.add(aggregator);

        return this;
    }

    public AggregatorGateway avg(@NonNull String rowColumn, @NonNull String statisticsColumn) {
        Aggregator aggregator = new AvgAggregator();

        aggregator.init(statistics, rowColumn, statisticsColumn);
        aggregatorList.add(aggregator);

        return this;
    }

}
