package com.onedot.win.framework.middleware.rdb.scanner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.onedot.win.common.util.lang.JSONUtil;
import com.onedot.win.framework.layer.domain.VO;

import java.util.Map;

/**
 * @author jings
 * @Date 2022/9/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableStepScanInitParams implements VO {
    /**
     * 单表、分库分表、单表多线程
     */
    private StepScanType scanType;

    /**
     * 单个步骤处理数量
     */
    private Integer stepBatchSize = 1000;

    /**
     * 指定开始边界。至少选其一
     */
    private String startDate;
    protected Long firstOffset;

    /**
     * 指定结束边界。至少选其一
     */
    private String endDate;
    protected Long endOffset;
    protected boolean toEnd;

    /**
     * 支持分库分表时，指定执行其中某分片
     */
    private Integer dbShard;
    private Integer tableShard;

    /**
     * 支持多线程处理时指定并发处理数量
     */
    @Builder.Default
    private Integer threadCnt = 1;

    private Map<String, Object> ext;

    public String toString() {
        return JSONUtil.toJSONString(this);
    }
}
