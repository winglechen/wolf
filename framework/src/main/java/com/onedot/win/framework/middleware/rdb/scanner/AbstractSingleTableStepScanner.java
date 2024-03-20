package com.onedot.win.framework.middleware.rdb.scanner;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jings
 * @Date 2022/12/18
 */
@Slf4j
public abstract class AbstractSingleTableStepScanner extends AbstractTableStepScanner {

    @Override
    public boolean supportScanType(StepScanType scanType) {
        return StepScanType.SINGLE_TABLE == scanType;
    }

    @Override
    public List<StepTaskParams> genStepTaskParams(String scanId, TableStepScanInitParams initParams) {

        Offset offset = calcOffset4FirstStep(initParams);
        if (offset == null) {
            log.info("[step_scanner] first offset is null, scan won't start. scanParams:{}", initParams);
            return Collections.emptyList();
        }
        // save task
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

        return Arrays.asList(stepTaskParams);
    }

    abstract public Offset calcOffset4FirstStep(TableStepScanInitParams initParams);

}
