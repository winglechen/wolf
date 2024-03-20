package com.onedot.win.framework.middleware.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.framework.middleware.sharding.util.TradeSharding;

import java.util.ArrayList;
import java.util.Collection;

/**
 * com.onedot.win.framework.middleware.sharding.algorithm
 *
 * @author Wingle
 * @since 2022/1/25 下午5:49
 **/
public class TradeHintAlgorithm implements HintShardingAlgorithm<Integer> {
    @Override
    public Collection<String> doSharding(Collection<String> shards, HintShardingValue<Integer> shardingValue) {
        if (null == shardingValue || CollectionUtil.isEmpty(shardingValue.getValues())) {
            return CollectionUtil.sub(shards, 1);
        }


        Collection<String> result = new ArrayList<>();
        for (Integer i : shardingValue.getValues()) {
            String s = TradeSharding.findShard(shards, Integer.toString(i));
            result.add(s);
        }

        if (CollectionUtil.isEmpty(result)) {
            return CollectionUtil.sub(shards, 1);
        }
        return result;
    }
}
