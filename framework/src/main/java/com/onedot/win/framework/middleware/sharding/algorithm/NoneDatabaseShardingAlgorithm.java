package com.onedot.win.framework.middleware.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

/**
 * com.onedot.win.framework.middleware.shardingjdbc.sharding
 *
 * @author Wingle
 * @since 2021/12/12 上午12:19
 **/
public class NoneDatabaseShardingAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {
    @Override
    public Collection<String> doSharding(Collection<String> shards, ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        return shards;
    }
}
