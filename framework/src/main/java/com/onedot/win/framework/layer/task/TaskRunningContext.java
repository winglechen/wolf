package com.onedot.win.framework.layer.task;

import lombok.Data;
import com.onedot.win.common.lang.contract.Context;

/**
 * @author weixing
 * @since 2022/8/17 16:10
 */
@Data
public class TaskRunningContext implements Context {
    protected int threadNum;
    protected int batchSize;
    protected boolean dryRun;
    protected String logTag;
}
