package com.wolf.framework.middleware.sharding.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import com.wolf.framework.util.id.TradeNo;
import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.common.util.collection.MapUtil;
import com.wolf.common.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * com.wolf.framework.middleware.sharding.util
 *
 * @author Wingle
 * @since 2022/1/22 下午7:40
 **/
@Slf4j
public class TradeSharding {
    // 之前的DB数量
    private final static int DEFAULT_DB_NUM = 4;
    // 扩展DB数量
    private final static int EXTEND_DB_NUM = 16;
    // 扩展DB开始的下标
    private final static int EXTEND_DB_START_INDEX = 16;
    // 扩展TABLE开始的下标
    private final static int EXTEND_TABLE_START_INDEX = 1024;
    // 扩展TABLE结束的下标
    private final static int EXTEND_TABLE_END_INDEX = 2047;

    private final static int DEFAULT_TABLE_NUM = 256;

    private final static int EXTEND_TABLE_NUM = 1024;


    public static Collection<String> shard(Collection<String> shards, ComplexKeysShardingValue<Comparable<?>> shardingValue, int shardNums) {
        Map<String, Collection<Comparable<?>>> valueMap = shardingValue.getColumnNameAndShardingValuesMap();
        if (MapUtil.isEmpty(valueMap)) {
            return CollectionUtil.sub(shards, 1);
        }

        Collection<String> result = new ArrayList<>();
        Set<Map.Entry<String, Collection<Comparable<?>>>> entrySet = valueMap.entrySet();

        boolean hasNewOrder = hasOrderNot2021(entrySet);
        for (Map.Entry<String, Collection<Comparable<?>>> entry : entrySet) {
            String column = entry.getKey();
            Collection<Comparable<?>> values = entry.getValue();

            if (StringUtil.endsWith(column, "_no", true)) {
                Collection<String> tmp = shardByNo(
                        shards,
                        values.stream().filter(item -> !isOrder2021((String) item)).collect(Collectors.toList()),
                        shardNums
                );
                if (CollectionUtil.notEmpty(tmp)) {
                    result.addAll(tmp);
                }
            } else if (StringUtil.endsWith(column, "_id", true) && !hasNewOrder) {
                Collection<String> tmp = shardById(shards, values, shardNums);
                if (CollectionUtil.notEmpty(tmp)) {
                    result.addAll(tmp);
                }
            }

            if (CollectionUtil.notEmpty(result)) {
                return result;
            }
        }

        return CollectionUtil.sub(shards, 1);
    }

    public static Collection<String> shardByNo(Collection<String> shards, Collection<Comparable<?>> values, int shardNums) {
        Collection<String> shardSet = new HashSet<>();
        for (Comparable<?> value : values) {
            if (!(value instanceof String)) {
                continue;
            }

            TradeNo tradeNo = TradeNo.of((String) value);
            if (tradeNo == null) {
                log.error("Can not parse tradeNo from value: {}", value);
                continue;
            }

            int v = tradeNo.getShard();

            String s;
            if (isOrder2021((String) value)) {
                s = findShard(shards, Integer.toString(v % shardNums));
            } else {
                s = findShardSupportExtendTable(shards, shardNums, (long) v);
            }

            if (StringUtil.notBlank(s)) {
                shardSet.add(s);
            }
        }

        return shardSet;
    }

    private static Collection<String> shardById(Collection<String> shards, Collection<Comparable<?>> values, int shardNums) {
        Collection<String> shardSet = new HashSet<>();
        for (Comparable<?> value : values) {
            if (!(value instanceof Number)) {
                continue;
            }

            long v = ((Number) value).longValue();
            long n = v % shardNums;

            String s = findShard(shards, Long.toString(n));

            if (StringUtil.notBlank(s)) {
                shardSet.add(s);
            }
        }

        return shardSet;
    }

    private static String findShardSupportExtendTable(Collection<String> shards, Integer shardNums, Long v) {
        long n;
        String s;
        if (shardNums == DEFAULT_DB_NUM) {
            // find database
            if (v >= EXTEND_TABLE_START_INDEX && v <= EXTEND_TABLE_END_INDEX) {
                n = v % EXTEND_DB_NUM;
                s = findShard(shards.stream().skip(shardNums).collect(Collectors.toList()), Long.toString(n + EXTEND_DB_START_INDEX));
            } else {
                n = v % DEFAULT_DB_NUM;
                s = findShard(shards.stream().limit(shardNums).collect(Collectors.toList()), Long.toString(n));
            }
        } else {
            // find table
            if (v >= EXTEND_TABLE_START_INDEX && v <= EXTEND_TABLE_END_INDEX) {
                n = v;
            } else {
                n = v % shardNums;
            }

            s = findShard(shards, Long.toString(n));
        }

        return s;
    }

    public static boolean isValidTableShard(int shard) {
        if (shard < DEFAULT_TABLE_NUM && shard >= 0) {
            return true;
        }

        return shard >= EXTEND_TABLE_START_INDEX && shard <= EXTEND_TABLE_END_INDEX;
    }

    public static Integer findDBShard(int shard) {
        if (shard >= EXTEND_TABLE_START_INDEX && shard <= EXTEND_TABLE_END_INDEX) {
            return shard % EXTEND_DB_NUM + EXTEND_DB_START_INDEX;
        }

        if (shard < EXTEND_TABLE_START_INDEX) {
            return shard % DEFAULT_DB_NUM;
        }

        return 0;
    }

    public static Integer findTableShard(int shard) {
        if (shard >= EXTEND_TABLE_START_INDEX && shard <= EXTEND_TABLE_END_INDEX) {
            return shard;
        }

        if (shard < EXTEND_TABLE_START_INDEX) {
            return shard % DEFAULT_TABLE_NUM;
        }

        return 0;
    }

    public static boolean isOrder2021(String no) {
        return StringUtil.startsWith(no, "2021", true);
    }

    private static boolean hasOrderNot2021(Collection<Comparable<?>> values) {
        boolean hasOrderNot2021 = false;

        for (Comparable<?> value : values) {
            if (!(value instanceof String)) {
                continue;
            }

            if (!isOrder2021((String) value)) {
                hasOrderNot2021 = true;
                break;
            }
        }

        return hasOrderNot2021;
    }

    private static boolean hasOrderNot2021(Set<Map.Entry<String, Collection<Comparable<?>>>> entrySet) {
        boolean result = false;

        for (Map.Entry<String, Collection<Comparable<?>>> entry : entrySet) {
            String column = entry.getKey();
            Collection<Comparable<?>> values = entry.getValue();

            if (StringUtil.endsWith(column, "_no", true)) {
                result = hasOrderNot2021(values);
            }
        }

        return result;

    }

    public static String findShard(Collection<String> shards, String suffix) {
        if (CollectionUtil.isEmpty(shards)) {
            return null;
        }

        boolean isFirst = true;
        for (String shard : shards) {
            if (StringUtil.isBlank(shard)) {
                continue;
            }

            if (isFirst) {
                String prefix = shard.contains("_") ? "_" : "-";
                suffix = StringUtil.join(prefix, suffix);
                isFirst = false;
            }

            if (StringUtil.endsWith(shard, suffix, true)) {
                return shard;
            }
        }

        return null;
    }

}
