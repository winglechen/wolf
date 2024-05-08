package com.wolf.framework.middleware.rdb.scanner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.wolf.common.util.lang.JSONUtil;
import com.wolf.framework.layer.domain.VO;

import java.util.Map;

/**
 * @author jings
 * @Date 2022/9/28
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StepTaskParams implements VO {
    /**
     * logical table name for sharding tables
     * or normal mysql table name for single shard tables.
     */
    private String table;
    private String scanId;

    private Long curOffset;
    private Long firstOffset;
    private Long endOffset;

    private Integer stepBatchSize;

    private Integer retryCnt;

    private StepScanType scanType;
    private Integer dbShard;
    private Integer tableShard;

    private TableStepScanInitParams initParams;

    private Map<String, Object> ext;

    public String toString() {
        return JSONUtil.toJSONString(this);
    }

    public void incrRetryCnt() {
        retryCnt = null == retryCnt ? 0 : ++retryCnt;
    }

    public Integer getRetryCnt() {
        return null == retryCnt ? 0 : retryCnt;
    }

    public void resetRetryCnt() {
        retryCnt = 0;
    }


}
