package com.wolf.framework.middleware.rdb.scanner;

import com.wolf.common.util.collection.CollectionUtil;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jings
 * @Date 2022/12/18
 */
@Slf4j
public abstract class AbstractMultiThreadTableStepScanner extends AbstractTableStepScanner {

    @Override
    public boolean supportScanType(StepScanType scanType) {
        return StepScanType.SINGLE_TABLE_MULTI_THREAD == scanType;
    }

    @Override
    public List<StepTaskParams> genStepTaskParams(String scanId, TableStepScanInitParams initParams) {

        List<Offset> offsetList = calcOffset4EveryThread(initParams);
        if (CollectionUtil.isEmpty(offsetList)) {
            log.info("[step_scanner] first offset is null, scan won't start. scanParams:{}", initParams);
            return Collections.emptyList();
        }
        // save task
        List<StepTaskParams> taskList = new LinkedList<>();
        for (Offset offset : offsetList) {
            StepTaskParams stepTaskParams = StepTaskParams.builder()
                    .table(getTableName())
                    .firstOffset(offset.getFirstOffset())
                    .endOffset(offset.getEndOffset())
                    .curOffset(offset.getFirstOffset())
                    .scanId(scanId)
                    .scanType(initParams.getScanType())
                    .stepBatchSize(initParams.getStepBatchSize())
                    .initParams(initParams)
                    .build();
            taskList.add(stepTaskParams);
        }
        return taskList;
    }

    abstract public List<Offset> calcOffset4EveryThread(TableStepScanInitParams initParams);

}
