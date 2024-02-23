package study.daydayup.wolf.framework.middleware.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import study.daydayup.wolf.framework.middleware.sharding.util.TradeSharding;

import java.util.Collection;

/**
 * study.daydayup.wolf.framework.middleware.shardingjdbc.sharding
 *
 * @author Wingle
 * @since 2021/12/12 上午12:19
 **/
public class TradeDbAlgorithm4 implements ComplexKeysShardingAlgorithm<Comparable<?>> {
    @Override
    public Collection<String> doSharding(Collection<String> shards, ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        return TradeSharding.shard(shards, shardingValue, 4);
    }

}
