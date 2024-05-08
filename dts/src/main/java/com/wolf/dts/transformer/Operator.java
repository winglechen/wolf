package com.wolf.dts.transformer;

import com.wolf.common.io.db.Row;
import com.wolf.common.util.collection.MapUtil;
import com.wolf.dts.transformer.aggregator.AggregatorGateway;
import com.wolf.dts.transformer.mapper.MapperGateway;
import com.wolf.dts.transformer.matcher.MatcherGateway;

/**
 * com.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/11 11:22 上午
 **/
public class Operator {
    private MapperGateway mapper;
    private MatcherGateway matcher;
    private AggregatorGateway aggregator;
    private Statistics statistics;

    public Operator() {
    }

    public Operator(Statistics statistics) {
        this.statistics = statistics;
    }

    public void merge(Row row) {
        if (null != aggregator) {
            aggregator.merge(row);
        }
    }

    public void format() {
        if (null != aggregator) {
            aggregator.format();
        }
    }

    public void operate(Row row) {
        if (MapUtil.isEmpty(row)) {
            return;
        }

        statistics.setMinAndMaxIds(row);
        if (mapper != null) {
            mapper.map(row);
        }

        if (null != matcher) {
            if (!matcher.match(row)) {
                return;
            }
        }

        if (null != aggregator) {
            aggregator.aggregate(row);
        }
    }

    public MatcherGateway match() {
        if (null == matcher) {
            matcher = new MatcherGateway();
        }

        return matcher;
    }

    public AggregatorGateway aggregate() {
        if (aggregator == null) {
            aggregator = new AggregatorGateway(statistics);
        }

        return aggregator;
    }

    public MapperGateway map() {
        if (mapper == null) {
            mapper = new MapperGateway();
        }

        return mapper;
    }
}
