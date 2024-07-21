package com.wolf.framework.middleware.rdb.scanner;

import com.wolf.framework.layer.domain.VO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jings
 * @Date 2022/9/28
 */
@Data
@AllArgsConstructor
public class DBTableShardPair implements VO {
    private Integer dbShard;
    private Integer tableShard;

    public static DBTableShardPair create(Integer dbShard, Integer tableShard) {
        return new DBTableShardPair(dbShard, tableShard);
    }
}
