package com.onedot.win.framework.middleware.rdb;

import lombok.extern.slf4j.Slf4j;
import com.onedot.win.common.io.db.Row;
import com.onedot.win.common.io.db.Table;
import com.onedot.win.common.io.enums.OrderEnum;
import com.onedot.win.common.util.time.DateUtil;

import java.time.LocalDateTime;

@Slf4j
public class TableOffset {
    public static final long MAX_LOOP_TIMES = 32;

    /**
     * 获取离目标时间比较接近的一个offset
     * <p>
     * 为了保证安全，会将目标时间向前推30分钟
     *
     * @param tableName       string
     * @param dbShard         int
     * @param tableShard      int
     * @param targetCreatedAt localDateTime
     * @return long
     */
    public static long getIdCreatedBefore(String tableName, Integer dbShard, Integer tableShard, LocalDateTime targetCreatedAt) {
        return getOffset(tableName, dbShard, tableShard, targetCreatedAt);
    }

    public static long getIdCreatedBefore(String tableName, LocalDateTime createdAt) {
        return getOffset(tableName, null, null, createdAt);
    }

    private static long getOffset(String tableName, Integer dbShard, Integer tableShard, LocalDateTime targetCreatedAt) {
        long minId = 0;
        long maxId = getLastId(tableName, dbShard, tableShard);

        if (maxId <= 0L) {
            return 0L;
        }

        long middleId = 0;
        int loopTimes = 0;
        targetCreatedAt = targetCreatedAt.minusMinutes(30);

        while (loopTimes < MAX_LOOP_TIMES) {

            middleId = getMiddleId(minId, maxId);

            if (findTheTarget(minId, middleId, maxId)) {
                return middleId;
            }

            Row row = getRow(tableName, dbShard, tableShard, middleId);
            if (row == null) {
                return middleId;
            }

            LocalDateTime createdAt = DateUtil.asLocalDateTime(row.getDate("created_at"));

            if (createdAt.isBefore(targetCreatedAt)) {
                minId = middleId;
            } else {
                maxId = middleId;
            }

            loopTimes++;
        }

        return middleId;
    }

    private static boolean findTheTarget(long minId, long middleId, long maxId) {
        return minId == middleId || minId + 1 > maxId;
    }

    private static long getMiddleId(long min, long max) {
        double m = (min + max) / 2.0;
        return (long) Math.floor(m);
    }

    public static long getLastId(String tableName) {
        return getLastId(tableName, null, null);
    }

    public static long getLastId(String tableName, Integer dbShard, Integer tableShard) {
        Table table = RDB.select("id, created_at")
                .from(tableName)
                .shard(dbShard, tableShard)
                .orderBy("id", OrderEnum.DESC)
                .limit(1)
                .execute()
                .getTable();

        if (table.isEmpty()) {
            return 0L;
        }
        return table.getLastId();
    }

    private static Row getRow(String tableName, Integer dbShard, Integer tableShard, long id) {
        Table table = RDB.select("id, created_at")
                .from(tableName)
                .shard(dbShard, tableShard)
                .where("id", ">=", id)
                .orderBy("id", OrderEnum.ASC)
                .limit(1)
                .execute()
                .getTable();

        if (table.isEmpty()) {
            return null;
        }

        return table.get(0);
    }
}
